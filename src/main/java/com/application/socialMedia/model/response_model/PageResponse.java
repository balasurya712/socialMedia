package com.application.socialMedia.model.response_model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse {
    private String _id;
    private String name;
    private String page_handle;
    private String bio;
    private byte[] dp;
    private Date dob;
    private String page_privacy;
    private Integer post_count ;
    private Integer follower_count ;
    private Integer following_count ;
}
