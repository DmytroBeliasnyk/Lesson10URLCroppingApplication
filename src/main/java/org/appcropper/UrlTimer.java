package org.appcropper;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class UrlTimer extends TimerTask {
    private long timeToLive;
    private UrlService urlService;
    private UrlShortedDTO shortedDTO;

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void setUrlService(UrlService urlService) {
        this.urlService = urlService;
    }

    public void setShortedDTO(UrlShortedDTO shortedDTO) {
        this.shortedDTO = shortedDTO;
    }

    @Override
    public void run() {
        Thread th = new Thread();
        try {
            th.join(TimeUnit.MINUTES.toMillis(timeToLive));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        urlService.deleteUrl(shortedDTO.getShortUrl());
    }
}
