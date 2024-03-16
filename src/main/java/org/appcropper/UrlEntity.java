package org.appcropper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UrlEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String url;

    public UrlEntity() {
    }

    public UrlEntity(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        urlShortedDTO.setShortUrl(Integer.toString(id));
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
