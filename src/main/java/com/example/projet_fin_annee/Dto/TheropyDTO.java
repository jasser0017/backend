package com.example.projet_fin_annee.Dto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TheropyDTO {
    private Long id;
    private String description;
    private String name;
    private byte[] imgbyte;
    private MultipartFile img;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	


	public byte[] getImgbyte() {
		return imgbyte;
	}

	public void setImgbyte(byte[] imgbyte) {
		this.imgbyte = imgbyte;
	}

	public MultipartFile getImg() {
		return img;
	}

	public void setImg(MultipartFile img) {
		this.img = img;
	}
	public String getImgUrl() {
        return "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(imgbyte);
    }
    
}
