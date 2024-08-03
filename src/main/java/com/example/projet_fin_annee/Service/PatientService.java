package com.example.projet_fin_annee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet_fin_annee.DTO.PatientDTO;
import com.example.projet_fin_annee.Entity.Patient;
import com.example.projet_fin_annee.Repository.IPatientRepository;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

@Service
public class PatientService implements IPatientService {

    private IPatientRepository patientRepository;
    private ModelMapper modelMapper; 

    @Autowired
    public PatientService(IPatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDTO save(PatientDTO patientDTO) {
        Patient patient = convertToEntity(patientDTO);
        patient = patientRepository.save(patient);
        return convertToDTO(patient);
    }

    @Override
    public Optional<PatientDTO> findById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.map(this::convertToDTO);
    }

    @Override
    public void update(PatientDTO patientDTO) {
        Patient patient = convertToEntity(patientDTO);
        patientRepository.save(patient);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(":Impossible de supprimer le patient avec son identifiant " + id);
        }
    }

    @Override
    public List<PatientDTO> findAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PatientDTO convertToDTO(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, Patient.class);
    }
}
