package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente crearPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente actualizarPaciente(Long id, Paciente pacienteActualizado) {
        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNombre(pacienteActualizado.getNombre());
                    paciente.setDni(pacienteActualizado.getDni());
                    paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
                    paciente.setTelefono(pacienteActualizado.getTelefono());
                    paciente.setCorreo(pacienteActualizado.getCorreo());
                    paciente.setActivo(pacienteActualizado.isActivo());
                    return pacienteRepository.save(paciente);
                }).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
