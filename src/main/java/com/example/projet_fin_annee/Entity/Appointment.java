package com.example.projet_fin_annee.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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
	
    

    
}
