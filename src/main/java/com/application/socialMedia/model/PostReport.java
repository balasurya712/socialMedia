package com.application.socialMedia.model;

import org.springframework.data.annotation.Id;
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
@Document(value = "post_report")
public class PostReport {
    @Id
    String _id;
    @DocumentReference(collection = "posts")
    private Post postId;
    @DocumentReference(collection = "page")
    private Page pageId;    

}
