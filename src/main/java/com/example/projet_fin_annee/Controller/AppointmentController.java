package com.example.projet_fin_annee.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projet_fin_annee.Dto.AppointmentDTO;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;
import com.example.projet_fin_annee.Service.IAppointmentService;

@RestController
@RequestMapping("/appoinments") // Tw marka7t path
public class AppointmentController {
	
	@Autowired
    private IAppointmentService appointmentService;


    
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<AppointmentDTO> appointmentToLookFor = appointmentService.findById(id);

        if(appointmentToLookFor.isPresent()) {
            return ResponseEntity.ok(appointmentToLookFor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        appointmentService.delete(id);
        return ResponseEntity.ok("elemination avec succes: " + id);
    }


}

