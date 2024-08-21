package com.example.projet_fin_annee.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projet_fin_annee.Entity.Appointment;
import com.example.projet_fin_annee.Entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

	
}
