package com.server.mshow.service;

import com.server.mshow.domain.Report;

import java.util.List;

public interface ReportService {

    Report getReport(String rid);
    List<Report> getReportByUser(String uid);
    void createReport(Report report);
    void cancelReport(String rid);//delete
}
