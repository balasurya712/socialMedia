package com.application.socialMedia.model;

import java.util.Date;

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
@Document(value = "posts")
public class Post {
    @Id
    private String _id;
    @DocumentReference(collection = "page")
    private Page pageId ;
    private String mediaUrl;
    private String primary_poster; 
    private Integer likeCount;
    private Integer commentCount; 
    private String caption; 
    private Date date_and_time;
}
