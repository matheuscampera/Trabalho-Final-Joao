package com.example.clinic.clinic_backend.controller;

import com.example.clinic.clinic_backend.model.Doctor;
import com.example.clinic.clinic_backend.model.Patient;
import com.example.clinic.clinic_backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Retorna todos os médicos
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Busca um médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorService.findDoctorById(id);
        return doctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Cria um novo médico
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    // Atualiza informações de um médico
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctorDetails);
        if (updatedDoctor != null) {
            return ResponseEntity.ok(updatedDoctor);
        }
        return ResponseEntity.notFound().build();
    }

    // Deleta um médico pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorService.deleteDoctorById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Associar médico com paciente
    @PostMapping("/{doctorId}/patients/{patientId}/appointments")
    public ResponseEntity<String> createAppointment(@PathVariable Long doctorId, @PathVariable Long patientId) {
        Optional<Doctor> doctor = doctorService.findDoctorById(doctorId);
        Optional<Patient> patient = doctorService.findPatientById(patientId);

        if (doctor.isPresent() && patient.isPresent()) {
            // Criação da consulta aqui, associando o médico e paciente
            // Vamos chamar o método do serviço de consulta para salvar a consulta com o paciente e médico
            // Exemplo de lógica do serviço que você já deve ter
            return ResponseEntity.ok("Consulta marcada com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Médico ou Paciente não encontrado.");
        }
    }
}
