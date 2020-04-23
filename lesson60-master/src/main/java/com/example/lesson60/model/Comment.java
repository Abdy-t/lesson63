package com.example.lesson60.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "comments")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Comment {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String idUser;
    private String idPhoto;
    private String commentText;
}
