package ApiRestClinica.clase36;

import ApiRestClinica.clase36.dto.TurnoDTO;
import ApiRestClinica.clase36.model.Domicilio;
import ApiRestClinica.clase36.model.Odontologo;
import ApiRestClinica.clase36.model.Paciente;
import ApiRestClinica.clase36.service.OdontologoService;
import ApiRestClinica.clase36.service.PacienteService;
import ApiRestClinica.clase36.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    private void cargarTurnoInicial() {
        Domicilio domicilio = new Domicilio("Calle a", 123, "Luján", "Mendoza");
        Paciente pacienteGuardado = new Paciente("Vanina", "Godoy", "1234", LocalDate.of(2023,06,29),
                domicilio, "mail@gmail.com");
        pacienteService.guardarPaciente(pacienteGuardado);
        Odontologo odontologoGuardado = new Odontologo("MP123", "Andrea", "Godoy");
        odontologoService.guardarOdontologo(odontologoGuardado);
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFecha(LocalDate.of(2023,06,29));
        turnoDTO.setPacienteId(pacienteGuardado.getId());
        turnoDTO.setOdontologoId(odontologoGuardado.getId());
        turnoService.guardarTurno(turnoDTO);
    }

    @Test
    public void listadoTurnoTest() throws Exception {
        cargarTurnoInicial();
        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }

}
