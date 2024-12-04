package com.example.clinic.clinic_backend.repository;

import com.example.clinic.clinic_backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Não é necessário implementar métodos, pois o JpaRepository já fornece CRUD básico
}
