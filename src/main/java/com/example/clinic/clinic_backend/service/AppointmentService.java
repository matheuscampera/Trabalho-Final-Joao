package com.example.clinic.clinic_backend.service;

import com.example.clinic.clinic_backend.model.Appointment;
import com.example.clinic.clinic_backend.model.Patient;
import com.example.clinic.clinic_backend.model.Doctor;
import com.example.clinic.clinic_backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    // Criar nova consulta
    public Appointment createAppointment(Appointment appointment) {
        try {
            // Verificando se o paciente existe
            Optional<Patient> patientOpt = patientService.getPatientById(appointment.getPatient().getId());
            if (!patientOpt.isPresent()) {
                throw new RuntimeException("Paciente não encontrado");
            }

            // Verificando se o médico existe
            Optional<Doctor> doctorOpt = doctorService.getDoctorById(appointment.getDoctor().getId());
            if (!doctorOpt.isPresent()) {
                throw new RuntimeException("Médico não encontrado");
            }

            // Associando os objetos de Patient e Doctor à consulta
            appointment.setPatient(patientOpt.get());
            appointment.setDoctor(doctorOpt.get());

            // Salvando a consulta no banco de dados
            return appointmentRepository.save(appointment);
        } catch (Exception e) {
            System.out.println("Erro ao criar consulta: " + e.getMessage());
            throw new RuntimeException("Erro ao criar consulta: " + e.getMessage());
        }
    }

    // Obter todas as consultas
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Obter consulta por ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Atualizar consulta
    public Optional<Appointment> updateAppointment(Long id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setDate(appointmentDetails.getDate());
            appointment.setReason(appointmentDetails.getReason());
            appointment.setStatus(appointmentDetails.getStatus());
            appointment.setPatient(appointmentDetails.getPatient());
            appointment.setDoctor(appointmentDetails.getDoctor());
            return Optional.of(appointmentRepository.save(appointment));
        }
        return Optional.empty();
    }

    // Excluir consulta
    public boolean deleteAppointmentById(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
