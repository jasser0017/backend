package com.example.projet_fin_annee.Controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.example.projet_fin_annee.DTO.ReceptionistDTO;
import com.example.projet_fin_annee.Service.ReceptionistServiceImpl;
import com.example.projet_fin_annee.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/") // tw marka7t path
public class RecptionistController {

   private ReceptionistServiceImpl receptionistServiceImpl;
   private ModelMapper modelMapper; // Utilisation de ModelMapper pour la conversion

   @Autowired
   public RecptionistController(ReceptionistServiceImpl receptionistServiceImpl, ModelMapper modelMapper) {
       this.receptionistServiceImpl = receptionistServiceImpl;
       this.modelMapper = modelMapper;
   }

   @GetMapping("/{id}")
   public ResponseEntity<ReceptionistDTO> findById(@PathVariable Long id) {
       Optional<ReceptionistDTO> receptionistDTO = receptionistServiceImpl.findById(id);

       if (receptionistDTO.isPresent()) {
           return ResponseEntity.ok(receptionistDTO.get());
       } else {
           return ResponseEntity.notFound().build();
       }
   }

   @PostMapping
   public ResponseEntity<ReceptionistDTO> save(@RequestBody ReceptionistDTO receptionistDTO) {
       ReceptionistDTO savedReceptionistDTO = receptionistServiceImpl.save(receptionistDTO);
       return ResponseEntity.ok(savedReceptionistDTO);
   }

   @PutMapping
   public ResponseEntity<String> update(@RequestBody ReceptionistDTO receptionistDTO) {
       ResponseEntity<String> response;
       Optional<ReceptionistDTO> receptionistDTOOptional = receptionistServiceImpl.findById(receptionistDTO.getId());

       if (receptionistDTOOptional.isPresent()) {
           receptionistServiceImpl.update(receptionistDTO);
           response = ResponseEntity.ok("Receptionist dont le nom a été mis à jour: " + receptionistDTO.getName());
       } else {
           response = ResponseEntity.notFound().build();
       }
       return response;
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
       receptionistServiceImpl.delete(id);
       return ResponseEntity.ok("Receptionist a été supprimé avec succès : " + id);
   }

   @GetMapping
   public ResponseEntity<List<ReceptionistDTO>> findAll() {
       List<ReceptionistDTO> receptionistsDTO = receptionistServiceImpl.findAll();
       return ResponseEntity.ok(receptionistsDTO);
   }

   @GetMapping("/registration/{registration}")
   public ResponseEntity<ReceptionistDTO> findByRegistration(@PathVariable Integer registration) throws Exception {
       Optional<ReceptionistDTO> receptionistDTO = receptionistServiceImpl.findByRegistration(registration);
       if (receptionistDTO.isPresent()) {
           return ResponseEntity.ok(receptionistDTO.get());
       } else {
           throw new Exception("Receptionist non trouvé pour le numéro de registration : " + registration);
       }
   }
}


