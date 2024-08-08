package com.example.projet_fin_annee.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet_fin_annee.Dto.AppointmentDTO;
import com.example.projet_fin_annee.Entity.Appointment;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;
import com.example.projet_fin_annee.Repository.IAppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService {

@Autowired
private IAppointmentRepository appointmentRepository;

@Override
public AppointmentDTO save(AppointmentDTO appointmentDTO) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException {
	// TODO Auto-generated method stub
	return Optional.empty();
}

@Override
public AppointmentDTO update(AppointmentDTO appointment) throws Exception {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Optional<AppointmentDTO> delete(Long id) throws ResourceNotFoundException {
	// TODO Auto-generated method stub
	return Optional.empty();
}

@Override
public List<AppointmentDTO> findAll() {
	// TODO Auto-generated method stub
	return null;
}



}

