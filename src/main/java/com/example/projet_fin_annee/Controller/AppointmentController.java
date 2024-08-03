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

import com.example.projet_fin_annee.DTO.AppointmentDTO;
import com.example.projet_fin_annee.Service.IAppointmentService;
import com.example.projet_fin_annee.Service.IPatientService;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/") // Tw marka7t path
public class AppointmentController {

    private IAppointmentService appointmentService;

  
    private IPatientService patientService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService, IPatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTO) {
        ResponseEntity<AppointmentDTO> response;

        
        if (patientService.findById(appointmentDTO.getPatient_id()).isPresent()) {
           
            response = ResponseEntity.ok(appointmentService.save(appointmentDTO));

        } else {
            
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
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

    @PutMapping
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO) throws Exception {
        ResponseEntity<AppointmentDTO> response;

        if (patientService.findById(appointmentDTO.getPatient_id()).isPresent()) {
            response = ResponseEntity.ok(appointmentService.update(appointmentDTO));

        } else {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        appointmentService.delete(id);
        return ResponseEntity.ok("elemination avec succes: " + id);
    }


}

