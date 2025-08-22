package com.example.demo.service;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paciente = new Paciente.Builder()
                .nombre("Juan Perez")
                .dni("12345678")
                .fechaNacimiento(LocalDate.of(1990,1,1))
                .telefono("123456789")
                .correo("juan@mail.com")
                .activo(true)
                .build();
    }

    @Test
    void testCrearPaciente() {
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        Paciente creado = pacienteService.crearPaciente(paciente);

        assertNotNull(creado);
        assertEquals("Juan Perez", creado.getNombre());
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testListarPacientes() {
        List<Paciente> lista = Arrays.asList(paciente);
        when(pacienteRepository.findAll()).thenReturn(lista);

        List<Paciente> resultado = pacienteService.listarPacientes();

        assertEquals(1, resultado.size());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPacientePorId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Optional<Paciente> opt = pacienteService.obtenerPacientePorId(1L);

        assertTrue(opt.isPresent());
        assertEquals("Juan Perez", opt.get().getNombre());
    }

    @Test
    void testActualizarPaciente() {
        Paciente actualizado = new Paciente.Builder()
                .nombre("Juan Actualizado")
                .dni("87654321")
                .fechaNacimiento(LocalDate.of(1991,2,2))
                .telefono("987654321")
                .correo("juan.actualizado@mail.com")
                .activo(false)
                .build();

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(actualizado);

        Paciente resultado = pacienteService.actualizarPaciente(1L, actualizado);

        assertEquals("Juan Actualizado", resultado.getNombre());
        assertEquals("87654321", resultado.getDni());
        verify(pacienteRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testEliminarPaciente() {
        doNothing().when(pacienteRepository).deleteById(1L);

        pacienteService.eliminarPaciente(1L);

        verify(pacienteRepository, times(1)).deleteById(1L);
    }
}
