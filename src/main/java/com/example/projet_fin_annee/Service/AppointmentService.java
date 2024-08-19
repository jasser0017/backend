package com.example.projet_fin_annee.Service;

import com.example.projet_fin_annee.Dto.AppointmentDTO;
import com.example.projet_fin_annee.Entity.Appointment;
import com.example.projet_fin_annee.Entity.User;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;
import com.example.projet_fin_annee.Repository.IAppointmentRepository;
import com.example.projet_fin_annee.Repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;
    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public Appointment createAppointment(AppointmentDTO appointmentDTO, String userEmail) {
        
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setDescription(appointmentDTO.getDescription());
        appointment.setStatus("Pending");

        
        appointment.setUser(user); 

        
        return appointmentRepository.save(appointment);
    }
   
    @Override
    public Appointment updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        if (appointmentRepository.existsById(id)) {
            Appointment appointment = new Appointment();
            appointment.setId(id);
            appointment.setDate(appointmentDTO.getDate());
            appointment.setDescription(appointmentDTO.getDescription());
            appointment.setStatus(appointmentDTO.getStatus());
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public void acceptAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointment.ifPresent(a -> {
            a.setStatus("Accepted");
            appointmentRepository.save(a);
            
        });
    }

    @Override
    public void rejectAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointment.ifPresent(a -> {
            a.setStatus("Rejected");
            appointmentRepository.save(a);
            
        });
    }
}
