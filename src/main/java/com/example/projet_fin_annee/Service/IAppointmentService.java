package com.example.projet_fin_annee.Service;

import java.util.List;
import java.util.Optional;

import com.example.projet_fin_annee.DTO.AppointmentDTO;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

public interface IAppointmentService {
    AppointmentDTO save (AppointmentDTO appointmentDTO);
    Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException;
    AppointmentDTO update(AppointmentDTO appointment) throws Exception;
    Optional<AppointmentDTO> delete(Long id) throws ResourceNotFoundException;
    List<AppointmentDTO> findAll();
}