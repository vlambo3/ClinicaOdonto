package ApiRestClinica.clase36.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    /*
    private TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
//        antes podia usar este metodo, pero ahora debemos responder un ResponseEntity
//        return turnoService.buscarTodosTurnos();
//        entonces vamos a usar un método estático
        return ResponseEntity.ok(turnoService.buscarTodosTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoXId(@PathVariable Integer id) {
    //tengo dos alternativas
        //vamos a optimizar el código, porque si la DB es muy grande, iría dos veces a hacer la búsqueda, se demoraría mucho
//
//        if (turnoService.buscarTurno(id) != null) {
//        return ResponseEntity.ok(turnoService.buscarTurno(id));
//    }
//    else {
//        //no existe el turno con el id ingresado
//        return ResponseEntity.notFound().build();
//    }

        Turno turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado != null) {
            return ResponseEntity.ok(turnoBuscado);
        }
        else {
            //no existe el turno con el id ingresado
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
        PacienteService pacienteService = new PacienteService();
        OdontologoService odontologoService = new OdontologoService();
        ResponseEntity<Turno> respuesta;

        if (pacienteService.buscarPaciente(turno.getPaciente().getId()) != null &&
                odontologoService.buscarOdontologoXId(turno.getOdontologo().getId()) != null) {
            //ambos existen en la BD
            //podemos registar el turno sin problemas, indicamos un 200OK

            respuesta = ResponseEntity.ok(turnoService.guardarTurno(turno));

        } else {
            //uno o ambos no existen, debemos bloquear la operacion
            respuesta = ResponseEntity.badRequest().build();

            //alternativa para seleccionar cualquier mejor cualquier código
            //respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        //verificar que el turno exista
        //contorl como el post
        PacienteService pacienteService = new PacienteService();
        OdontologoService odontologoService = new OdontologoService();
        ResponseEntity<Turno> respuesta;

        if (turnoService.buscarTurno(turno.getId()) != null) {
            //es un id válido
            if (pacienteService.buscarPaciente(turno.getPaciente().getId()) != null &&
                    odontologoService.buscarOdontologoXId(turno.getOdontologo().getId()) != null) {
                //ambos existen en la BD
                //podemos registar el turno sin problemas, indicamos un 200OK
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se actualizó el turno con id = "+ turno.getId());

            } else {
                //uno o ambos no existen, debemos bloquear la operacion
                return ResponseEntity.badRequest().body("Error al actualizar, verificar si el odontologo "+
                        "o el paciente existe en la DB");

                //alternativa para seleccionar cualquier mejor cualquier código
                //respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        else {
            //error con el id
            return ResponseEntity.badRequest().body("No se puede actualizar un turno que no exista en la Base de Datos");
        }



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) {
        if (turnoService.buscarTurno(id) != null) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().body("Se eliminó el turno con id = " + id);
        }
        else {
            return ResponseEntity.ok().body("No se puede eliminar el turno con id = " + id
            + " ya que el mismo no existe en la BD.");
        }
    }

     */
}