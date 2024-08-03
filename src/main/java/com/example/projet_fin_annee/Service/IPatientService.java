package com.example.projet_fin_annee.Service;

import java.util.List;
import java.util.Optional;

import com.example.projet_fin_annee.DTO.PatientDTO;
import com.example.projet_fin_annee.Entity.Patient;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

public interface IPatientService {
    PatientDTO save (PatientDTO patientDTO);
    Optional<PatientDTO> findById(Long id);
    void update(PatientDTO patientDTO);
    void delete(Long id) throws ResourceNotFoundException;
    List<PatientDTO> findAll();
}
