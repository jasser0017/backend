package com.example.projet_fin_annee.Service;

import java.util.List;
import java.util.Optional;

import com.example.projet_fin_annee.Dto.AppointmentDTO;
import com.example.projet_fin_annee.Dto.TheropyDTO;
import com.example.projet_fin_annee.Entity.Appointment;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;

public interface IAppointmentService {
	
    Appointment updateAppointment(Long id, AppointmentDTO appointmentDTO);
    Optional<Appointment> getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    void acceptAppointment(Long id);
    void rejectAppointment(Long id);
	
	Appointment createAppointment(AppointmentDTO appointmentDTO, String userEmail);
}