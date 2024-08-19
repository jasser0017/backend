package com.example.projet_fin_annee.Dto;

import java.time.LocalDate;

public class PatientDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private LocalDate admissionOfDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getAdmissionOfDate() {
		return admissionOfDate;
	}
	public void setAdmissionOfDate(LocalDate admissionOfDate) {
		this.admissionOfDate = admissionOfDate;
	}

    // Constructors, Getters, and Setters
}
