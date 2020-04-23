package com.example.lesson60.service;

import com.example.lesson60.model.Publication;
import com.example.lesson60.repository.PubRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PubService {
    private final PubRepository pubRepository;

    public PubService(PubRepository pubRepository) {
        this.pubRepository = pubRepository;
    }

    public List<Publication> getPublications() {
        var a = pubRepository.findAll();
        List<Publication> publications = StreamSupport.stream(a.spliterator(), false)
                .collect(Collectors.toList());
        return publications;
    }

    public void saveNewPhoto(String idUser, String photo, String description){
        var publication = Publication.builder()
                .idUser(idUser)
                .photo(photo)
                .description(description)
                .build();
        pubRepository.save(publication);
    }

}
