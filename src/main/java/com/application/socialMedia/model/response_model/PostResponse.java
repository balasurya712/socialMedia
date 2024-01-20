package com.application.socialMedia.model.response_model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String _id;
    private String pageId ;
    private MultipartFile media;
    private String primary_poster; 
    private Integer likeCount = 0;
    private Integer commentCount = 0; 
    private String caption; 
    private Date date_and_time = new Date();    
}