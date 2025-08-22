package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cita;
import com.example.demo.service.CitaService;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Map<String, Object> payload) {
        try {
            Long pacienteId = Long.valueOf(payload.get("pacienteId").toString());
            Long doctorId = Long.valueOf(payload.get("doctorId").toString());
            LocalDate fecha = LocalDate.parse(payload.get("fecha").toString());
            LocalTime hora = LocalTime.parse(payload.get("hora").toString());
            String estado = payload.get("estado").toString();
            String notas = payload.getOrDefault("notas", "").toString();

            Cita cita = citaService.crearCita(pacienteId, doctorId, fecha, hora, estado, notas);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listarCitas() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCitaPorId(@PathVariable Long id) {
        return citaService.obtenerCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        try {
            LocalDate fecha = payload.containsKey("fecha") ? LocalDate.parse(payload.get("fecha").toString()) : null;
            LocalTime hora = payload.containsKey("hora") ? LocalTime.parse(payload.get("hora").toString()) : null;
            String estado = (String) payload.getOrDefault("estado", null);
            String notas = (String) payload.getOrDefault("notas", null);

            Cita citaActualizada = citaService.actualizarCita(id, fecha, hora, estado, notas);
            return ResponseEntity.ok(citaActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }
}
