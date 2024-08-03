package com.example.projet_fin_annee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projet_fin_annee.Entity.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}