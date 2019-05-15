package com.server.mshow.service.impl;

import com.server.mshow.dao.AppointmentMapper;
import com.server.mshow.domain.Appointment;
import com.server.mshow.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AppointmentService")
public class AppointmentServiceImpl implements AppointmentService {

   //@Autowired
   private AppointmentMapper appointmentMapper;

    @Override
    public Appointment getAppointment(String aid) {
        return appointmentMapper.getAppointment(aid);
    }

    @Override
    public List<Appointment> getAppointmentListByUid(String uid) {
        return appointmentMapper.getAppointmentListByUid(uid);
    }

    @Override
    public List<Appointment> getAppointmentListBySid(String sid) {
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

    @Override
    public void cancelAppointment(String aid) {
        Appointment appointment = getAppointment(aid);
        appointment.setStatus("取消订单");
        appointmentMapper.updateAppointment(appointment);
    }


}
