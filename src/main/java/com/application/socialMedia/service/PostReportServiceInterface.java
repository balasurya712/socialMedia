package com.application.socialMedia.service;

import java.util.List;

import com.application.socialMedia.model.PostReport;

public interface PostReportServiceInterface {
    PostReport report(PostReport post);
    List<PostReport>  viewReport();
}
