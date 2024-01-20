package com.application.socialMedia.service;

import org.springframework.web.multipart.MultipartFile;

import com.application.socialMedia.model.Page;
import com.application.socialMedia.model.response_model.PageResponse;

public interface PageServiceInterface {
    PageResponse getPageResponseById(String id);
    Page save(Page page,MultipartFile file);
    Page getPageById(String id);
    boolean updateDP(String id, MultipartFile file);
    boolean updateBio(String id, String bio);
    boolean updatePageHandle(String id, String pageHandle);
    boolean isAccountPrivate(String id);
}
