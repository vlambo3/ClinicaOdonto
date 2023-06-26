package ApiRestClinica.clase36.service;

import ApiRestClinica.clase36.model.Domicilio;
import ApiRestClinica.clase36.model.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar = new Paciente("Vanina", "Godoy", "12345"
                , LocalDate.of(2023,6, 26),new Domicilio("Calle A", 111, "Mendoza",
                "Mendoza"),"prueba@gmail.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarPacientesTest(){
        List<Paciente> pacientes = pacienteService.buscarTodosPacientes();
        //por la cantidad de pacientes
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, pacientes.size());
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Paciente pacienteAActualizar = new Paciente(1L, "Andrea", "Godoy", "12345"
                , LocalDate.of(2023,6, 26),new Domicilio(1L, "Calle A", 111, "Mendoza",
                "Mendoza"),"prueba@gmail.com");
        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPaciente(pacienteAActualizar.getId());
        assertEquals("Andrea", pacienteActualizado.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarPacienteTest(){
        Long idAEliminar = 1L;
        pacienteService.eliminarPaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(idAEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }

}