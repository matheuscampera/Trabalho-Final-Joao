package com.example.clinic.clinic_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Chave primária, gerada automaticamente

    private String name;      // Nome do paciente
    private String birthDate; // Data de nascimento
    private String cpf;       // CPF do paciente
    private String phone;     // Telefone do paciente
    private String email;     // E-mail do paciente
}
