package com.server.mshow.service.impl;

import com.server.mshow.dao.ReportMapper;
import com.server.mshow.domain.Report;
import com.server.mshow.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ReportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Report getReport(int rid) {
        return reportMapper.getReport(rid);
    }

    @Override
    public List<Report> getReportByUser(int uid) {
        return reportMapper.getReportByUser(uid);
    }

    @Override
    public void createReport(Report report) {
        reportMapper.createReport(report);
    }

    @Override
    public void cancelReport(int rid) {
        reportMapper.cancelReport(rid);
    }
}
