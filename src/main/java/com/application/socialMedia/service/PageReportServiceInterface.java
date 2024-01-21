package com.application.socialMedia.service;

import java.util.List;

import com.application.socialMedia.model.PageReport;

public interface PageReportServiceInterface {
    PageReport report(PageReport page);
    List<PageReport>  viewReport();
    Boolean deleteReport(String pageId);
    Boolean deleteReportAndPage(String pageId);
}
