package com.example.projet_fin_annee.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.projet_fin_annee.Dto.TheropyDTO;
import com.example.projet_fin_annee.Entity.Theropy;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;

public interface ITheropyService {
   
    public TheropyDTO addTheropy(TheropyDTO theropyDTO) throws IOException;
    
    boolean delete(Long id) ;
    List<TheropyDTO> findAll();
    public Optional<Theropy> findById(Long id);
    public TheropyDTO updateTheropy(Long id, TheropyDTO theropyDTO) throws IOException,ResourceNotFoundException;
    
}
