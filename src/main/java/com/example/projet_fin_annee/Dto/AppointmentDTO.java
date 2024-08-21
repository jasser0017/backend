package com.example.projet_fin_annee.Dto;

import java.time.LocalDate;

import com.example.projet_fin_annee.Entity.User;

public class AppointmentDTO {

    private Long id;
    private String date;
   
    private String status;
    private UserDTO user;
    private Long theropyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

   

    public String getDate() {
		return date;
	}

	public Long getTheropyId() {
		return theropyId;
	}

	public void setTheropyId(Long theropyId) {
		this.theropyId = theropyId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	

	
	

    
}
