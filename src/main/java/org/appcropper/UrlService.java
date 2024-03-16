package org.appcropper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional
    public String saveUrl(UrlDTO urlDTO) {
        UrlEntity urlEntity = urlRepository.findByUrl(urlDTO.getUrl());
        if (urlEntity == null) {
            urlEntity = new UrlEntity(urlDTO.getUrl());
            urlRepository.save(urlEntity);
        }
        return urlEntity.getId();
    }

    @Transactional
    public String getUrl(String id) {
        Optional<UrlEntity> urlOpt = urlRepository.findById(id);
        if (urlOpt.isEmpty())
            return null;
        UrlEntity urlEntity = urlOpt.get();
        return urlEntity.getUrl();
    }

    @Transactional(readOnly = true)
    public List<UrlShortedDTO> getAll() {
        List<UrlEntity> list = urlRepository.findAll();
        List<UrlShortedDTO> result = new ArrayList<>();
        list.forEach(u -> result.add(u.toShortedDTO()));
        return result;
    }
    @Transactional
    public void deleteUrl(String id){
        urlRepository.deleteById(id);
    }
}
