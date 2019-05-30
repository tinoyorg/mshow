package com.server.mshow.service;

import com.server.mshow.domain.Report;

import java.util.List;

public interface ReportService {

    Report getReport(int rid);
    List<Report> getReportByUser(int uid);
    void createReport(Report report);
    void cancelReport(int rid);//delete
    void cancelReportByObject(int uid,int object_id,String object_type);
}
