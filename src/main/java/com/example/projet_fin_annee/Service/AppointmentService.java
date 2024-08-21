package com.example.projet_fin_annee.Service;

import com.example.projet_fin_annee.Dto.AppointmentDTO;
import com.example.projet_fin_annee.Dto.UserDTO;
import com.example.projet_fin_annee.Entity.Appointment;
import com.example.projet_fin_annee.Entity.Theropy;
import com.example.projet_fin_annee.Entity.User;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;
import com.example.projet_fin_annee.Repository.IAppointmentRepository;
import com.example.projet_fin_annee.Repository.ITheropyRepository;
import com.example.projet_fin_annee.Repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITheropyRepository theropyRepository;
    @Autowired
    private EmailService emailService;
    private static final String STATIC_EMAIL_ADDRESS = "jasserallela007@gmail.com";
    
    
    
    public LocalDate convertFromStringToLocalDate(String date) {
        try {
            
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            
            System.err.println("Date invalide : " + date);
            return null; 
        }
    }
    
    public User convertUserDtoToUser(UserDTO userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        
        
        String[] nameParts = userDto.getName() != null ? userDto.getName().split(" ", 2) : new String[]{"", ""};
        
        user.setFirstname(nameParts.length > 0 ? nameParts[0] : "");
        user.setLastname(nameParts.length > 1 ? nameParts[1] : "");
        user.setEmail(userDto.getEmail());
        user.setId(userDto.getId());
        
        
        return user;
    }

    
    @Override
    public Appointment createAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        Appointment appointment = new Appointment();
        appointment.setDate(convertFromStringToLocalDate(appointmentDTO.getDate()));
        
        appointment.setId(appointmentDTO.getId());
        
        appointment.setStatus("Pending");
        
        Theropy theropy = theropyRepository.findById(appointmentDTO.getTheropyId())
            .orElseThrow(() -> new ResourceNotFoundException("Theropy not found"));
        
        appointment.setTheropy(theropy);
        
        
        appointment.setUser(convertUserDtoToUser(appointmentDTO.getUser()));
       
        
       // appointment.setUser();
        
        return appointmentRepository.save(appointment);
    }
   
    @Override
    public Appointment updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        if (appointmentRepository.existsById(id)) {
            Appointment appointment = new Appointment();
            appointment.setId(id);
            
            appointment.setDate(convertFromStringToLocalDate(appointmentDTO.getDate()));
            
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
            String emailText = "Votre rendez-vous du " + a.getDate() + " a été accepté.";
            emailService.sendEmail(STATIC_EMAIL_ADDRESS, "Rendez-vous Accepté", emailText);
        
            
        });
    }

    @Override
    public void rejectAppointment(Long id) {
    	
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointment.ifPresent(a -> {
            a.setStatus("Rejected");
            appointmentRepository.save(a);
            String emailText = "Désolé, votre rendez-vous du " + a.getDate() + " a été rejeté. Veuillez choisir une autre date.";
            emailService.sendEmail(STATIC_EMAIL_ADDRESS, "Rendez-vous Rejeté", emailText);
            
        });
    }
}
