package com.example.projet_fin_annee.Dto;

public class TheropyDTO {
	private Long id;
	private String discription;
	private String name;
	private byte[] image;
	
	
	public TheropyDTO () {}
	
	public TheropyDTO(Long id,String discription,String name ) {
		this.discription=discription;
		this.id=id;
		this.name=name;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDiscription() {
		return discription;
	}


	public void setDiscription(String discription) {
		this.discription = discription;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	

}
