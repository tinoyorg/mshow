package com.server.mshow.service;

import com.server.mshow.domain.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment getAppointment(int aid);
    List <Appointment> getAppointmentListByUid(int uid);
    List <Appointment> getAppointmentListBySid(int sid);
    void createAppointment(Appointment appointment);// post 用户预约参观某一个展览
    void updateAppointment(Appointment appointment);//update 用户修改预约记录，预约状态为成功

}
