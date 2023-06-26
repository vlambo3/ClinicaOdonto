package ApiRestClinica.clase36.controller;

import ApiRestClinica.clase36.model.Paciente;
import ApiRestClinica.clase36.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes() {
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se eliminó al paciente con id = " + id);
        } else {
            return ResponseEntity.badRequest().body("No se encuentra un paciente con id = " + id);
        }

    }

    @GetMapping("/email")
    public String traerPacienteXMail(Model model, @RequestParam("email") String email) {
         Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteByEmail(email);
         return "index";
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizó el paciente con apellido " + paciente.getApellido());
        } else {
            return ResponseEntity.badRequest().body("El paciente con id= " + paciente.getId() +  " no existe en la DB." +
                    " No puede actualizar algo que no existe");
        }

    }

}
