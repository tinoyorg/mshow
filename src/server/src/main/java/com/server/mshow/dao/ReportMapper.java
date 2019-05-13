package com.server.mshow.dao;

import com.server.mshow.domain.Report;

import java.util.List;

public interface ReportMapper {

    Report getReport(String rid);
    List<Report> getReportByUser(String uid);
    void createReport(Report report);
    void cancelReport(String rid);//delete
}
