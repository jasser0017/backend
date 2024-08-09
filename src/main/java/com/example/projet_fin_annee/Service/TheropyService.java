package com.example.projet_fin_annee.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet_fin_annee.Dto.TheropyDTO;
import com.example.projet_fin_annee.Entity.Theropy;
import com.example.projet_fin_annee.Repository.ITheropyRepository;


@Service
public class TheropyService implements ITheropyService {

    @Autowired
    private ITheropyRepository theropyRepository;

    @Override
    public List<TheropyDTO> findAll() {
        List<Theropy> theropies = theropyRepository.findAll();
        List<TheropyDTO> theropiesDtos = new ArrayList<>();

        for (Theropy theropy : theropies) {
        	theropiesDtos.add(new TheropyDTO(theropy
        			.getId(),theropy.getDescription(),theropy.getName()));
        }

        return theropiesDtos;
    }
}
