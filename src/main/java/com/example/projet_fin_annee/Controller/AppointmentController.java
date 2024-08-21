package com.example.projet_fin_annee.Controller;

import com.example.projet_fin_annee.Dto.AppointmentDTO;
import com.example.projet_fin_annee.Entity.Appointment;
import com.example.projet_fin_annee.Exceptions.ResourceNotFoundException;
import com.example.projet_fin_annee.Service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

   
   
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(
            @RequestBody AppointmentDTO appointmentDTO
            ) throws ResourceNotFoundException {
        
        
        Appointment createdAppointment = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.ok(createdAppointment);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDTO);
        return updatedAppointment != null ? ResponseEntity.ok(updatedAppointment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<Void> acceptAppointment(@PathVariable Long id) {
        appointmentService.acceptAppointment(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> rejectAppointment(@PathVariable Long id) {
        appointmentService.rejectAppointment(id);
        return ResponseEntity.ok().build();
    }
}
