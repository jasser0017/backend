package com.example.projet_fin_annee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet_fin_annee.DTO.ReceptionistDTO;
import com.example.projet_fin_annee.Entity.Receptionist;
import com.example.projet_fin_annee.Repository.IReceptionistRepository;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

@Service
public class ReceptionistServiceImpl implements IReceptionistService {

    private final IReceptionistRepository receptionistRepository;
    private final ModelMapper modelMapper; // Utilisation de ModelMapper pour la conversion

    @Autowired
    public ReceptionistServiceImpl(IReceptionistRepository receptionistRepository, ModelMapper modelMapper) {
        this.receptionistRepository = receptionistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReceptionistDTO save(ReceptionistDTO receptionistDTO) {
        Receptionist receptionist = modelMapper.map(receptionistDTO, Receptionist.class);
        Receptionist savedReceptionist = receptionistRepository.save(receptionist);
        return modelMapper.map(savedReceptionist, ReceptionistDTO.class);
    }

    @Override
    public Optional<ReceptionistDTO> findById(Long id) {
        return receptionistRepository.findById(id)
                .map(receptionist -> modelMapper.map(receptionist, ReceptionistDTO.class));
    }

    @Override
    public void update(ReceptionistDTO receptionistDTO) {
        Receptionist receptionist = modelMapper.map(receptionistDTO, Receptionist.class);
        receptionistRepository.save(receptionist);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (receptionistRepository.existsById(id)) {
            receptionistRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Impossible de supprimer le receptionist avec l'identifiant: " + id);
        }
    }

    @Override
    public List<ReceptionistDTO> findAll() {
        return receptionistRepository.findAll().stream()
                .map(receptionist -> modelMapper.map(receptionist, ReceptionistDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReceptionistDTO> findByRegistration(Integer registration) {
        return receptionistRepository.findByRegistration(registration)
                .map(receptionist -> modelMapper.map(receptionist, ReceptionistDTO.class));
    }
}
