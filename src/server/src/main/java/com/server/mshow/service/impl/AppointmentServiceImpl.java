package com.server.mshow.service.impl;

import com.server.mshow.dao.AppointmentMapper;
import com.server.mshow.domain.Appointment;
import com.server.mshow.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

   //@Autowired
   private AppointmentMapper appointmentMapper;

    @Override
    public Appointment getAppointment(String aid) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentListByUid(String uid) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentListBySid(String sid) {
        return null;
    }

    @Override
    public void createAppointment(Appointment appointment) {

    }

    @Override
    public void updateAppointment(Appointment appointment) {

    }

    @Override
    public void cancelAppointment(String aid) {

    }


}
