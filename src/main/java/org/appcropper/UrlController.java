package org.appcropper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("shorten")
    public UrlShortedDTO shorten(@RequestParam String url,
                                 @RequestParam long time) {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setUrl(url);
        String id = urlService.saveUrl(urlDTO);

        UrlShortedDTO shorted = new UrlShortedDTO();
        shorted.setShortUrl(id);
        shorted.setUrl(urlDTO.getUrl());

        Thread th = new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.MINUTES.toMillis(time));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            urlService.deleteUrl(id);
        });
        th.setDaemon(true);
        th.start();

        return shorted;
    }

    @GetMapping("get_all")
    public List<UrlShortedDTO> show() {
        return urlService.getAll();
    }

    @GetMapping("my/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        String url = urlService.getUrl(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("delete")
    public List<UrlShortedDTO> delete(@RequestParam String shortUrl) {
        urlService.deleteUrl(shortUrl);
        return urlService.getAll();
    }
}
