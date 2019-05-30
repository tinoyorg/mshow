package com.server.mshow.dao;

import com.server.mshow.domain.Appointment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AppointmentMapper {
    Appointment getAppointment(int aid);
    List<Appointment> getAppointmentListByUid(int uid);
    List <Appointment> getAppointmentListBySid(int sid);
    void createAppointment(Appointment appointment);// post 用户预约参观某一个展览
    void updateAppointment(Appointment appointment);//update 用户修改预约记录，预约状态为成功
    void deleteAppointmentBySid(int sid);
}
