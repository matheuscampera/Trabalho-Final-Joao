package com.example.clinic.clinic_backend.controller;

import com.example.clinic.clinic_backend.model.Patient;
import com.example.clinic.clinic_backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients") // Define o prefixo da URL para todos os endpoints deste controlador
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping // Endpoint para buscar todos os pacientes
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}") // Endpoint para buscar um paciente pelo ID
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findPatientById(id);
        return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // Endpoint para criar um novo paciente
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @PutMapping("/{id}") // Endpoint para atualizar um paciente
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Optional<Patient> optionalPatient = patientService.findPatientById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setName(patientDetails.getName());
            patient.setBirthDate(patientDetails.getBirthDate());
            patient.setCpf(patientDetails.getCpf());
            patient.setPhone(patientDetails.getPhone());
            patient.setEmail(patientDetails.getEmail());
            return ResponseEntity.ok(patientService.savePatient(patient));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // Endpoint para deletar um paciente
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientService.existsById(id)) {
            patientService.deletePatientById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
