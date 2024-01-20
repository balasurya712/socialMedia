package com.application.socialMedia.model;

import java.util.ArrayList;

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
@Document(value = "follow_request")
public class FollowRequest {
    private String pageId;
    @DocumentReference(collection = "page")
    private ArrayList<Page> requestedId ;
}
