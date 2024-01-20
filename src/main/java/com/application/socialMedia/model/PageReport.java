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
@Document(value = "page_report")
public class PageReport {
    @Id
    private String _id;
    @DocumentReference(collection = "page")
    private Page pageId;
    @DocumentReference(collection = "page")
    private Page repoterId;
    private String message;
}
