package com.example.projet_fin_annee.Dto;

public class UserDTO {
	
	private String firstname;
	private String lastname;
	private String email;
	private Long id;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
	

}
