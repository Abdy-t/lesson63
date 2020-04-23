package com.example.lesson60.repository;

import com.example.lesson60.model.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PubRepository extends CrudRepository<Publication, String> {
//    Publication getById(String id);
//    List<Publication> queryAll();
}