package com.example.lesson60.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "likes")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Like {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String markAccount;
    private String markPhoto;
    private Date markDate;

}
