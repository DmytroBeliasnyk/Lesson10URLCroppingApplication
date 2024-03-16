package org.appcropper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UrlEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String url;

    public UrlEntity() {
    }

    public UrlEntity(String url) {
        this.url = url;
        String shortedUrl = url.substring(url.indexOf(":") + 3,
                url.indexOf("/", url.indexOf(":") + 3));
        this.id = shortedUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UrlShortedDTO toShortedDTO() {
        UrlShortedDTO urlShortedDTO = new UrlShortedDTO();
        urlShortedDTO.setUrl(url);
        urlShortedDTO.setShortUrl(id);
        return urlShortedDTO;
    }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
