package com.example.lesson60.repository;

import com.example.lesson60.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface ComRepository extends CrudRepository<Comment, String> {
}
