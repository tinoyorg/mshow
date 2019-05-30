package com.server.mshow.dao;

import com.server.mshow.domain.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ReportMapper {

    Report getReport(int rid);
    List<Report> getReportByUser(int uid);
    void createReport(Report report);
    void cancelReport(int rid);//delete
    void cancelReportByObject(@Param("uid") int uid, @Param("object_id") int object_id, @Param("object_type") String object_type);

}
