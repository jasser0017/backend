package com.example.projet_fin_annee.Entity;



import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import com.example.projet_fin_annee.Dto.TheropyDTO;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "THEROPY")
@Getter
@Setter
public class Theropy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(columnDefinition="oid")
    @Lob
    
    private byte[] img;


    
    
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
	
	public TheropyDTO getDto() {
		TheropyDTO theropyDTO = new TheropyDTO();
		theropyDTO.setId(id);
		theropyDTO.setName(name);
		theropyDTO.setDescription(description);
		theropyDTO.setImgbyte(img);
		return theropyDTO;
		
		
	}


    
}
