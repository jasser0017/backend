package com.example.projet_fin_annee.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projet_fin_annee.Entity.Receptionist;

public interface IReceptionistRepository extends JpaRepository<Receptionist, Long> {

    Optional<Receptionist> findByRegistration(Integer registration);

    
}