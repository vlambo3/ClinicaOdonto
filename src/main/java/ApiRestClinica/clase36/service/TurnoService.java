package ApiRestClinica.clase36.service;

import ApiRestClinica.clase36.dto.TurnoDTO;
import ApiRestClinica.clase36.model.Odontologo;
import ApiRestClinica.clase36.model.Paciente;
import ApiRestClinica.clase36.model.Turno;
import ApiRestClinica.clase36.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public TurnoDTO guardarTurno(TurnoDTO turno) {
        Turno turnoAGuardar = turnoDTOaTurno(turno);
        Turno turnoGuardado =  turnoRepository.save(turnoAGuardar);
        return turnoATurnoDTO(turnoGuardado);
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    public void actualizarPaciente(TurnoDTO turno) {
        Turno turnoAActualizar = turnoDTOaTurno(turno);
        turnoRepository.save(turnoAActualizar);
    }

    public Optional<TurnoDTO> buscarTurno(Long id) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else {
            return Optional.empty();
        }
    }

    public List<TurnoDTO> buscarTodosLosTurnos() {
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> respuesta = new ArrayList<>();
        for (Turno t: turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(t));
        }
        return respuesta;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno) {
        TurnoDTO respuesta =  new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFecha());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        return respuesta;
    }

    private Turno turnoDTOaTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();
        //cargar cada elemento
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        //asociar cada elemento
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        return turno;
    }


}
