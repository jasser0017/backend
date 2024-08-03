package com.example.projet_fin_annee.DTO;

import java.time.LocalDate;

public class PatientDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private LocalDate admissionOfDate;
   

    public PatientDTO() {
        
    }

    
    public PatientDTO(Long id, String name, String lastName, String email, LocalDate admissionOfDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.admissionOfDate = admissionOfDate;
    }

   

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

}