package com.example.projet_fin_annee.Service;

import java.util.List;
import java.util.Optional;

import com.example.projet_fin_annee.DTO.ReceptionistDTO;
import com.example.projet_fin_annee.Entity.Receptionist;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

public interface IReceptionistService {
    ReceptionistDTO save (ReceptionistDTO receptionistDTO);
    Optional<ReceptionistDTO> findById(Long id);
    void update(ReceptionistDTO receptionistDTO);
    void delete(Long id) throws ResourceNotFoundException;
    List<ReceptionistDTO> findAll();
    Optional<ReceptionistDTO> findByRegistration(Integer registration);
}

