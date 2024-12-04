package com.example.clinic.clinic_backend.service;

import com.example.clinic.clinic_backend.model.Patient;
import com.example.clinic.clinic_backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Obtém todos os pacientes
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Busca um paciente por ID
    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Novo método para ser usado no AppointmentService
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Verifica se um paciente existe pelo ID
    public boolean existsById(Long id) {
        return patientRepository.existsById(id);
    }

    // Cria ou salva um paciente no banco de dados
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Atualiza informações de um paciente existente
    public Patient updatePatient(Long id, Patient patientDetails) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setName(patientDetails.getName());
            patient.setBirthDate(patientDetails.getBirthDate());
            patient.setCpf(patientDetails.getCpf());
            patient.setPhone(patientDetails.getPhone());
            patient.setEmail(patientDetails.getEmail());
            return patientRepository.save(patient);
        }
        return null; // Pode ser substituído por uma exceção customizada
    }

    // Deleta um paciente por ID
    public boolean deletePatientById(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
