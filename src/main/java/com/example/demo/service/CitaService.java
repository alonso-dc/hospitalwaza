package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cita;
import com.example.demo.model.Doctor;
import com.example.demo.model.Paciente;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PacienteRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Cita crearCita(Long pacienteId, Long doctorId, LocalDate fecha, LocalTime hora, String estado, String notas) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        Cita cita = new Cita.Builder()
                .paciente(paciente)
                .doctor(doctor)
                .fecha(fecha)
                .hora(hora)
                .estado(estado)
                .notas(notas)
                .activo(true)
                .build();

        return citaRepository.save(cita);
    }

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    public Cita actualizarCita(Long id, LocalDate fecha, LocalTime hora, String estado, String notas) {
        return citaRepository.findById(id)
                .map(cita -> {
                    Cita citaActualizada = new Cita.Builder()
                            .id(cita.getId())
                            .paciente(cita.getPaciente())
                            .doctor(cita.getDoctor())
                            .fecha(fecha != null ? fecha : cita.getFecha())
                            .hora(hora != null ? hora : cita.getHora())
                            .estado(estado != null ? estado : cita.getEstado())
                            .notas(notas != null ? notas : cita.getNotas())
                            .activo(cita.isActivo())
                            .build();

                    return citaRepository.save(citaActualizada);
                }).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
}
