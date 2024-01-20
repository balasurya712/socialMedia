package com.application.socialMedia.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @Builder

@Document(value = "page")
public class Page {
    @Id
    private String _id;
    private String name;
    private String page_handle;
    private String bio;
    private String dp = "";
    private Date dob;
    private String page_privacy = "private";
    private Integer post_count = 0;
    private Integer follower_count = 0;
    private Integer following_count = 0;
}
