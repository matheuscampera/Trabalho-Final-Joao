package com.example.clinic.clinic_backend.service;

import com.example.clinic.clinic_backend.model.Doctor;
import com.example.clinic.clinic_backend.model.Patient;
import com.example.clinic.clinic_backend.repository.DoctorRepository;
import com.example.clinic.clinic_backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // Obtém todos os médicos
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Busca um médico por ID
    public Optional<Doctor> findDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Novo método para ser usado no AppointmentService
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Verifica se um médico existe pelo ID
    public boolean existsById(Long id) {
        return doctorRepository.existsById(id);
    }

    // Salva ou cria um médico
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Atualiza informações de um médico existente
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setName(doctorDetails.getName());
            doctor.setSpecialty(doctorDetails.getSpecialty());
            doctor.setCrm(doctorDetails.getCrm());
            doctor.setPhone(doctorDetails.getPhone());
            doctor.setEmail(doctorDetails.getEmail());
            return doctorRepository.save(doctor);
        }
        return null; // Pode ser substituído por uma exceção customizada
    }

    // Deleta um médico por ID
    public boolean deleteDoctorById(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar paciente por ID
    public Optional<Patient> findPatientById(Long patientId) {
        return patientRepository.findById(patientId);
    }
}
