package com.example.projet_fin_annee.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet_fin_annee.DTO.AppointmentDTO;
import com.example.projet_fin_annee.Entity.Appointment;
import com.example.projet_fin_annee.Entity.Patient;
import com.example.projet_fin_annee.Repository.IAppointmentRepository;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

@Service
public class AppointmentService implements IAppointmentService {

private IAppointmentRepository appointmentRepository;

@Autowired
public AppointmentService(IAppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
}

@Override
public AppointmentDTO save(AppointmentDTO appointmentDTO) {
   
    Appointment appointmentEntity = new Appointment();

    
    Patient patientEntity = new Patient();
    patientEntity.setId(appointmentDTO.getPatient_id());
    
    appointmentEntity.setPatient(patientEntity);
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(appointmentDTO.getDate(), formatter);

    
    appointmentEntity.setDate(date);

   
    appointmentRepository.save(appointmentEntity);

    
    AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();

    
    appointmentDTOToReturn.setId(appointmentEntity.getId());
    appointmentDTOToReturn.setDate(appointmentEntity.getDate().toString());
    
    appointmentDTOToReturn.setPatient_id(appointmentEntity.getPatient().getId());

    return appointmentDTOToReturn;
}

@Override
public Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException {
    
    Optional<Appointment> appointmentToLookFor = appointmentRepository.findById(id);

    
    Optional<AppointmentDTO> appointmentDTO = null;

    if (appointmentToLookFor.isPresent()) {
        
        Appointment appointment = appointmentToLookFor.get();

        AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
        appointmentDTOToReturn.setId(appointment.getId());
        appointmentDTOToReturn.setPatient_id(appointment.getPatient().getId());
        
        appointmentDTOToReturn.setDate(appointment.getDate().toString());

        appointmentDTO = Optional.of(appointmentDTOToReturn);
        return appointmentDTO;

    } else {
        throw new ResourceNotFoundException("identifiant introuvable: " + id);
    }




}

@Override
public AppointmentDTO update(AppointmentDTO appointmentDTO) throws Exception {

    
    if(appointmentRepository.findById(appointmentDTO.getId()).isPresent()) {

       
        Optional<Appointment> appointmentEntity = appointmentRepository.findById(appointmentDTO.getId());

        
        Patient patientEntity = new Patient();
        patientEntity.setId(appointmentDTO.getPatient_id());


       
        appointmentEntity.get().setPatient(patientEntity);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(appointmentDTO.getDate(), formatter);

        
        appointmentEntity.get().setDate(date);

        
        appointmentRepository.save(appointmentEntity.get());

        
        AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
        appointmentDTOToReturn.setId(appointmentEntity.get().getId());
        appointmentDTOToReturn.setPatient_id(appointmentEntity.get().getPatient().getId());
        
        appointmentDTOToReturn.setDate(appointmentEntity.get().getDate().toString());

        return appointmentDTOToReturn;
    } else {
        throw new Exception("Échec de la mise à jour");
    }

}

@Override
public Optional<AppointmentDTO> delete(Long id) throws ResourceNotFoundException {
    
    Optional<Appointment> appointmentToLookFor = appointmentRepository.findById(id);

    Optional<AppointmentDTO> appointmentDTO;
    if (appointmentToLookFor.isPresent()) {
        
        Appointment appointment =  appointmentToLookFor.get();

        
        AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
        appointmentDTOToReturn.setId(appointment.getId());
        appointmentDTOToReturn.setPatient_id(appointment.getPatient().getId());
        appointmentDTOToReturn.setDate(appointment.getDate().toString());

        appointmentDTO = Optional.of(appointmentDTOToReturn);
        return appointmentDTO;

    } else {
        
        throw new ResourceNotFoundException("identifiant introuvable: " + id);
    }

}

@Override
public List<AppointmentDTO> findAll() {
    
    List<Appointment> appointments = appointmentRepository.findAll();

    
    List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

    
    
    for (Appointment appointment: appointments) {
        appointmentDTOS.add(new AppointmentDTO(appointment.getId(),
                appointment.getPatient().getId(),
                appointment.getDate().toString()));
    }

    return appointmentDTOS;
}
}

