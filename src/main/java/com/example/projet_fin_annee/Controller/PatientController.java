package com.example.projet_fin_annee.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projet_fin_annee.DTO.PatientDTO;
import com.example.projet_fin_annee.Service.IPatientService;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/")// tw marka7t path
public class PatientController {

    private IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

 
    @PostMapping
    public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO patientDTO) {
        PatientDTO savedPatient = patientService.save(patientDTO);
        return ResponseEntity.ok(savedPatient);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        patientDTO.setId(id); // Assurez-vous que l'ID est défini sur le DTO
        patientService.update(patientDTO);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() {
        List<PatientDTO> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        patientService.delete(id);
        return ResponseEntity.ok(": suprresion avec succes" + id);
    }
}
