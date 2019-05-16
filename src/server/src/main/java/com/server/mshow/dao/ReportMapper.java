package com.server.mshow.dao;

import com.server.mshow.domain.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReportMapper {

    Report getReport(int rid);
    List<Report> getReportByUser(int uid);
    void createReport(Report report);
    void cancelReport(int rid);//delete
}
