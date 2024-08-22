package com.example.projet_fin_annee.Entity;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.projet_fin_annee.Dto.AppointmentDTO;
import com.example.projet_fin_annee.Dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
 import com.example.projet_fin_annee.Service.AppointmentService;

import jakarta.persistence.*;

@Entity
@Table(name = "APPOINTMENT")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    private LocalDate date;

    

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;


    @Column(name = "STATUS")
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY,optional= false)
    @JoinColumn(name = "theropy_id", referencedColumnName = "ID", nullable = false)
    @JsonBackReference
    private Theropy theropy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Theropy getTheropy() {
		return theropy;
	}

	

	public void setTheropy(Theropy theropy) {
		this.theropy = theropy;
		
	}
	
	public AppointmentDTO getDto() {
		AppointmentDTO appointmentDto = new AppointmentDTO();
		appointmentDto.setId(id);
		appointmentDto.setStatus(status);
		appointmentDto.setTheropyId(id);
		appointmentDto.setUser(convertUsertoUserDTO(user));
		appointmentDto.setDate(convertFromLocalDateToString(date));
		
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

	        return user1;
	    }
	 public String convertFromLocalDateToString(LocalDate date) {
	        if (date == null) {
	            System.err.println("Date invalide : null");
	            return null;
	        }
	        return date.toString();
	    }

	
	
    

    
}
