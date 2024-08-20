package com.example.projet_fin_annee.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet_fin_annee.Dto.TheropyDTO;
import com.example.projet_fin_annee.Entity.Theropy;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;
import com.example.projet_fin_annee.Repository.ITheropyRepository;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;

@Service
public class TheropyService implements ITheropyService {

    @Autowired
    private ITheropyRepository theropyRepository;

   
    public TheropyDTO addTheropy(TheropyDTO theropyDTO) throws IOException {
        Theropy theropy = new Theropy();
        theropy.setName(theropyDTO.getName());
        theropy.setDescription(theropyDTO.getDescription());
        if (theropyDTO.getImg() != null) {
            theropy.setImg(theropyDTO.getImg().getBytes());
        }
        Theropy savedTheropy = theropyRepository.save(theropy);
        return savedTheropy.getDto();
    }

    @Override
    public List<TheropyDTO> findAll(){
    	List<Theropy> theropies = theropyRepository.findAll();
    	return theropies.stream().map(Theropy::getDto).collect(Collectors.toList());
    }

 
   

 
    @Override
    public boolean delete(Long id)  {
    	Optional<Theropy> optionalTheropt=theropyRepository.findById(id);
        if (optionalTheropt.isPresent()) {
            theropyRepository.deleteById(id);
            return true;
        }  
        return false;
    }
    
    @Override
    public Optional<Theropy> findById(Long id) {
        return theropyRepository.findById(id);
    }
    
    @Override
    public TheropyDTO updateTheropy(Long id, TheropyDTO theropyDTO) throws IOException,ResourceNotFoundException {
        Optional<Theropy> optionalTheropy = theropyRepository.findById(id);
        if (optionalTheropy.isPresent()) {
            Theropy theropy = optionalTheropy.get();
            theropy.setName(theropyDTO.getName());
            theropy.setDescription(theropyDTO.getDescription());
            if (theropyDTO.getImg() != null) {
                theropy.setImg(theropyDTO.getImg().getBytes());
            }
            return theropyRepository.save(theropy).getDto();
        } else {
            throw new ResourceNotFoundException("Theropy with id " + id + " not found");
        }
    }

}
  
