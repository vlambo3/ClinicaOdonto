package ApiRestClinica.clase36.controller;

import ApiRestClinica.clase36.dto.TurnoDTO;
import ApiRestClinica.clase36.exception.BadRequestException;
import ApiRestClinica.clase36.model.Turno;
import ApiRestClinica.clase36.service.OdontologoService;
import ApiRestClinica.clase36.service.PacienteService;
import ApiRestClinica.clase36.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodosLosTurnos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable("id") Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else {
            //no existe el turno con el id ingresado
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException  {
        ResponseEntity<TurnoDTO> respuesta;

        if (pacienteService.buscarPaciente(turno.getPacienteId()).isPresent() &&
                odontologoService.buscarOdontologoXId(turno.getOdontologoId()).isPresent()) {
            respuesta = ResponseEntity.ok(turnoService.guardarTurno(turno));

        } else {
            throw new BadRequestException("No se puede registrar un turno cuando no exista, cuando "
                    + "no exista un odontologo y/o un paciente");
        }
        return respuesta;
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        ResponseEntity<TurnoDTO> respuesta;

        if (turnoService.buscarTurno(turno.getId()).isPresent()) {
            //es un id válido
            if (pacienteService.buscarPaciente(turno.getPacienteId()).isPresent() &&
                    odontologoService.buscarOdontologoXId(turno.getOdontologoId()).isPresent()) {
                respuesta = ResponseEntity.ok(turnoService.guardarTurno(turno));
                return ResponseEntity.ok("Se actualizó el turno con id = "+ turno.getId());

            } else {
                return ResponseEntity.badRequest().body("Error al actualizar, verificar si el " +
                        "odontologo y/o el paciente existen en la base de datos.");
            }
        }
        else {
            //error con el id
            return ResponseEntity.badRequest().body("No se puede actualizar un turno que no exista en la Base de Datos");
        }



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        if (turnoService.buscarTurno(id).isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().body("Se eliminó el turno con id = " + id);
        }
        else {
            return ResponseEntity.ok().body("No se puede eliminar el turno con id = " + id
            + " ya que el mismo no existe en la BD.");
        }
    }


}