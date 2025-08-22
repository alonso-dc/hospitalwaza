package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDate fecha;

    private LocalTime hora;

    private String estado;

    private String notas;

    private boolean activo;

    private Cita(Builder builder) {
        this.id = builder.id;
        this.paciente = builder.paciente;
        this.doctor = builder.doctor;
        this.fecha = builder.fecha;
        this.hora = builder.hora;
        this.estado = builder.estado;
        this.notas = builder.notas;
        this.activo = builder.activo;
    }

    public Cita() {}

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public String getNotas() {
        return notas;
    }

    public boolean isActivo() {
        return activo;
    }

    public static class Builder {
        private Long id;
        private Paciente paciente;
        private Doctor doctor;
        private LocalDate fecha;
        private LocalTime hora;
        private String estado;
        private String notas;
        private boolean activo = true;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder paciente(Paciente paciente) {
            this.paciente = paciente;
            return this;
        }

        public Builder doctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder hora(LocalTime hora) {
            this.hora = hora;
            return this;
        }

        public Builder estado(String estado) {
            this.estado = estado;
            return this;
        }

        public Builder notas(String notas) {
            this.notas = notas;
            return this;
        }

        public Builder activo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public Cita build() {
             if (paciente == null) {
                throw new IllegalStateException("Paciente es obligatorio");
            }
            if (doctor == null) {
                throw new IllegalStateException("Doctor es obligatorio");
            }
            if (fecha == null) {
                throw new IllegalStateException("Fecha es obligatoria");
            }
            if (hora == null) {
                throw new IllegalStateException("Hora es obligatoria");
            }
            if (estado == null || estado.isBlank()) {
                throw new IllegalStateException("Estado es obligatorio");
            }
            return new Cita(this);
        }
    }
}
