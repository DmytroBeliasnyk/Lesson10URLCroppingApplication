package org.appcropper;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, String> {
    public UrlEntity findByUrl(String url);
}
