package com.example.clinic.clinic_backend.controller;

import com.example.clinic.clinic_backend.model.Appointment;
import com.example.clinic.clinic_backend.model.Patient;
import com.example.clinic.clinic_backend.model.Doctor;
import com.example.clinic.clinic_backend.service.AppointmentService;
import com.example.clinic.clinic_backend.service.PatientService;
import com.example.clinic.clinic_backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping
    public ResponseEntity<Object> createAppointment(@RequestBody Appointment appointment) {
        try {
            System.out.println(appointment);
            Patient patient = patientService.getPatientById(appointment.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

            Doctor doctor = doctorService.getDoctorById(appointment.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

            appointment.setPatient(patient);
            appointment.setDoctor(doctor);

            Appointment createdAppointment = appointmentService.createAppointment(appointment);
            

            return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar consulta: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
        Optional<Appointment> updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
        return updatedAppointment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        if (appointmentService.deleteAppointmentById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
