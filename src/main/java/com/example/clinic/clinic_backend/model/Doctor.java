package com.example.clinic.clinic_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      // Chave primária, gerada automaticamente

    private String name;      // Nome do médico
    private String specialty; // Especialidade do médico
    private String crm;       // Número do CRM do médico
    private String phone;     // Telefone do médico
    private String email;     // E-mail do médico
}
