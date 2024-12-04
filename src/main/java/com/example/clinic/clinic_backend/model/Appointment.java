package com.example.clinic.clinic_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // Chave primária, gerada automaticamente

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")  // Formato para data e hora
    private LocalDateTime date;  // Data e hora da consulta

    private String reason;       // Motivo da consulta
    private String status;       // Status da consulta (ex: "agendada", "realizada", etc.)

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)  // Relacionamento com o paciente
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)   // Relacionamento com o médico
    private Doctor doctor;
}
