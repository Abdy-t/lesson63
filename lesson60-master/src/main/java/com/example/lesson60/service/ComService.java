package com.example.lesson60.service;

import com.example.lesson60.model.Comment;
import com.example.lesson60.model.Publication;
import com.example.lesson60.repository.ComRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ComService {
    private final ComRepository comRepository;

    public ComService(ComRepository comRepository) {
        this.comRepository = comRepository;
    }

    public List<Comment> getComments() {
        var a = comRepository.findAll();
        List<Comment> comments = StreamSupport.stream(a.spliterator(), false)
                .collect(Collectors.toList());
        return comments;
    }

    public void saveNewComment(String idUser, String idPhoto, String commentText) {
        var comment = Comment.builder()
                .idUser(idUser)
                .idPhoto(idPhoto)
                .commentText(commentText)
                .build();
        comRepository.save(comment);
    }

}
