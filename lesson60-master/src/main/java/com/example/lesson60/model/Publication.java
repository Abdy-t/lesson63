package com.example.lesson60.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "publications")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Publication {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String idUser;
    private String photo;
    private String description;

}