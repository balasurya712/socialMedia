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
@Document(value = "follow_details")
public class FollowDetail {
    @DocumentReference(collection = "page")
    private Page followerId;
    @DocumentReference(collection = "page")
    private Page followingId;
}
