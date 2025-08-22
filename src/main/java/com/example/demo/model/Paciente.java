package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String correo;
    private boolean activo;

    private Paciente() {}

    public static class Builder {
        private Paciente paciente;

        public Builder() {
            paciente = new Paciente();
        }

        public Builder nombre(String nombre) {
            paciente.setNombre(nombre);
            return this;
        }

        public Builder dni(String dni) {
            paciente.setDni(dni);
            return this;
        }

        public Builder fechaNacimiento(LocalDate fechaNacimiento) {
            paciente.setFechaNacimiento(fechaNacimiento);
            return this;
        }

        public Builder telefono(String telefono) {
            paciente.setTelefono(telefono);
            return this;
        }

        public Builder correo(String correo) {
            paciente.setCorreo(correo);
            return this;
        }

        public Builder activo(boolean activo) {
            paciente.setActivo(activo);
            return this;
        }

        public Paciente build() {
            return paciente;
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

    	
}
