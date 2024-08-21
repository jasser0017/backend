package com.example.projet_fin_annee.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projet_fin_annee.Entity.Theropy;
import com.example.projet_fin_annee.Entity.User;

public interface ITheropyRepository extends JpaRepository<Theropy, Long> {

	Optional<Theropy> findByName(String theropyName);
	
	 
	
	

}
