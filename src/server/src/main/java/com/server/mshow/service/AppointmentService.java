package com.server.mshow.service;

import com.server.mshow.domain.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment getAppointment(String aid);
    List <Appointment> getAppointmentListByUid(String uid);
    List <Appointment> getAppointmentListBySid(String sid);
    void createAppointment(Appointment appointment);// post 用户预约参观某一个展览
    void updateAppointment(Appointment appointment);//update 用户修改预约记录，预约状态为成功
    void cancelAppointment(String aid);//update 用户取消预约参观某一个展览，修改预约状态为取消
}
