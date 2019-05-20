package com.server.mshow.service.impl;

import com.server.mshow.dao.AppointmentMapper;
import com.server.mshow.domain.Appointment;
import com.server.mshow.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AppointmentService")
public class AppointmentServiceImpl implements AppointmentService {

   @Autowired
   private AppointmentMapper appointmentMapper;

    @Override
    public Appointment getAppointment(int aid) {
        return appointmentMapper.getAppointment(aid);
    }

    @Override
    public List<Appointment> getAppointmentListByUid(int uid) {
        return appointmentMapper.getAppointmentListByUid(uid);
    }

    @Override
    public List<Appointment> getAppointmentListBySid(int sid) {
        return appointmentMapper.getAppointmentListBySid(sid);
    }

    @Override
    public void createAppointment(Appointment appointment) {
        appointmentMapper.createAppointment(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentMapper.updateAppointment(appointment);
    }



}
