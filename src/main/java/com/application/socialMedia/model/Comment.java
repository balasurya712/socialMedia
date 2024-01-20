package com.application.socialMedia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "comments")
public class Comment {
    @Id
    private String _id;
    private String postOrCommentId;
    private String type;
    @DocumentReference(collection = "page")
    private Page pageId;
    private String comment;
    private Integer likeCount = 0;
    private Integer commentCount = 0;
}
