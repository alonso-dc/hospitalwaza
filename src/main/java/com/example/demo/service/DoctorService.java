package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor crearDoctor(Doctor doctor) {

        return doctorRepository.save(doctor);
    }

    public List<Doctor> listarDoctores() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> obtenerDoctorPorId(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor actualizarDoctor(Long id, Doctor doctorActualizado) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setNombre(doctorActualizado.getNombre());
                    doctor.setEspecialidad(doctorActualizado.getEspecialidad());
                    doctor.setTelefono(doctorActualizado.getTelefono());
                    doctor.setCorreo(doctorActualizado.getCorreo());
                    doctor.setActivo(doctorActualizado.isActivo());
                    return doctorRepository.save(doctor);
                }).orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
    }

    public void eliminarDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
