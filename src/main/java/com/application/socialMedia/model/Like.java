package com.application.socialMedia.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "likes")
public class Like {
    private String postOrCommentId;
    private String type;
    @DocumentReference(collection = "page")
    private Page likedId;
}
