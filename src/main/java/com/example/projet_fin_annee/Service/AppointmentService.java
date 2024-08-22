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
import java.util.stream.Collectors;

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
    
    public User convertUserDTOtoUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user1 = new User();
        user1.setId(userDTO.getId());
        user1.setFirstname(userDTO.getFirstname());
        user1.setLastname(userDTO.getLastname());
        user1.setEmail(userDTO.getEmail());

        return user1;
    }


    
    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        Appointment appointment = new Appointment();
        appointment.setDate(convertFromStringToLocalDate(appointmentDTO.getDate()));
        
        appointment.setId(appointmentDTO.getId());
        
        appointment.setStatus("Pending");
        
        Theropy theropy = theropyRepository.findById(appointmentDTO.getTheropyId())
                .orElseThrow(() -> new ResourceNotFoundException("Theropy not found"));
        
        appointment.setTheropy(theropy);
        
        
        appointment.setUser(convertUserDTOtoUser(appointmentDTO.getUser()));
       
        
       // appointment.setUser();
        
        
        return appointmentRepository.save(appointment).getDto();
    }
   
    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        if (appointmentRepository.existsById(id)) {
            Appointment appointment = new Appointment();
            appointment.setId(id);
            
            appointment.setDate(convertFromStringToLocalDate(appointmentDTO.getDate()));
            
            appointment.setStatus(appointmentDTO.getStatus());
            return appointmentRepository.save(appointment).getDto();
        }
        return null;
    }

    @Override
    public Optional<AppointmentDTO> getAppointmentById(Long id) {
        return appointmentRepository.findById(id).map(this::getDto);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream().map(Appointment::getDto).collect(Collectors.toList());
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
    @Override
    public void deleteAppointment(Long appointmentId) throws ResourceNotFoundException {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new ResourceNotFoundException("Appointment not found with ID: " + appointmentId);
        }
        appointmentRepository.deleteById(appointmentId);
    }

    public AppointmentDTO getDto(Appointment appointment) {
		AppointmentDTO appointmentDto = new AppointmentDTO();
		appointmentDto.setId(appointment.getId());
		appointmentDto.setStatus(appointment.getStatus());
		appointmentDto.setTheropyId(appointment.getId());
		appointmentDto.setUser(convertUsertoUserDTO(appointment.getUser()));
		appointmentDto.setDate(convertFromLocalDateToString(appointment.getDate()));
		
		return appointmentDto;
		
		
    }
    public UserDTO convertUsertoUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO user1 = new UserDTO();
        user1.setId(user.getId());
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());

        return user1;}
	
		 public String convertFromLocalDateToString(LocalDate date) {
		        if (date == null) {
		            System.err.println("Date invalide : null");
		            return null;
		        }
		        return date.toString();
		    

		
		
	}
    
    
}
