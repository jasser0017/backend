package com.example.projet_fin_annee.Service;

import java.util.List;
import java.util.Optional;

import com.example.projet_fin_annee.Dto.AppointmentDTO;
import com.example.projet_fin_annee.Dto.TheropyDTO;
import com.example.projet_fin_annee.Entity.Appointment;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;

public interface IAppointmentService {
	
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO);
    Optional<AppointmentDTO> getAppointmentById(Long id);
    List<AppointmentDTO> getAllAppointments();
    void acceptAppointment(Long id);
    void rejectAppointment(Long id);
	
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException;
	void deleteAppointment(Long appointmentId) throws ResourceNotFoundException;
}