package com.handy.web.HandyGuys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handy.web.HandyGuys.Models.Appointment;
import com.handy.web.HandyGuys.repository.IAppointmentRepository;

@Service
public class AppointmentService {
    
    @Autowired
    IAppointmentRepository repo;

    public String saveAppointment(Appointment appointment){
        repo.save(appointment);
        return "Appointment saved successfully";
    }

    public String updateAppointment(Appointment appointment){
        Optional<Appointment> oldAppointment = repo.findById(appointment.getId());
        if(oldAppointment.isPresent()){
            Appointment updatedAppointment = oldAppointment.get();
            updatedAppointment.setDateOfAppointment(appointment.getDateOfAppointment());
            updatedAppointment.setPurpose(appointment.getPurpose());
            updatedAppointment.setStatus(appointment.getStatus());
            updatedAppointment.setClient_address(appointment.getClient_address());
            repo.save(updatedAppointment);
            return "Appointment updated successfully";
        }
        return "Appointment not found";
    }

    
}
