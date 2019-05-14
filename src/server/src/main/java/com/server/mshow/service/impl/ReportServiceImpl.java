package com.server.mshow.service.impl;

import com.server.mshow.dao.ReportMapper;
import com.server.mshow.domain.Report;
import com.server.mshow.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ReportService")
public class ReportServiceImpl implements ReportService {

    //@Autowired
    private ReportMapper reportMapper;

    @Override
    public Report getReport(String rid) {
        return null;
    }

    @Override
    public List<Report> getReportByUser(String uid) {
        return null;
    }

    @Override
    public void createReport(Report report) {

    }

    @Override
    public void cancelReport(String rid) {

    }
}
