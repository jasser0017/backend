package com.example.projet_fin_annee.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.projet_fin_annee.Dto.TheropyDTO;
import com.example.projet_fin_annee.Entity.Theropy;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;
import com.example.projet_fin_annee.Service.ITheropyService;

@RestController
@RequestMapping( value="/theropies")


public class TheropyController {

    @Autowired
    private ITheropyService theropyService;

    @PostMapping("/add")
    public ResponseEntity<TheropyDTO> addTheropy(
    	    @RequestParam("name") String name,
    	    @RequestParam("description") String description,
    	    @RequestParam("img") MultipartFile img) throws IOException {

    	    TheropyDTO theropyDTO = new TheropyDTO();
    	    theropyDTO.setName(name);
    	    theropyDTO.setDescription(description);
    	    theropyDTO.setImg(img);

    	    TheropyDTO theropyDTO1 = theropyService.addTheropy(theropyDTO);
    	    return ResponseEntity.status(HttpStatus.CREATED).body(theropyDTO1);
    	}

    @GetMapping("/getAll")
    public ResponseEntity<List<TheropyDTO>> findAll() {
        List<TheropyDTO> theropies = theropyService.findAll();
        return ResponseEntity.ok(theropies);
    }

  

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheropy(@PathVariable Long id) {
    	boolean delated = theropyService.delete(id);
    	if(delated) {
    		return ResponseEntity.noContent().build();
    		
    	}
    	return ResponseEntity.notFound().build();
    }
  
    
}
