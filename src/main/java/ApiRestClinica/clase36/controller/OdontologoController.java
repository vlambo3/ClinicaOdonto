package ApiRestClinica.clase36.controller;

import ApiRestClinica.clase36.exception.ResourceNotFoundException;
import ApiRestClinica.clase36.model.Odontologo;
import ApiRestClinica.clase36.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//va controller porque va a trabajar con vistas
//@Controller
@RestController
//va request -solicitud- mapping -mapear- porque le vamos a decir que se mapee a esta ruta
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos() {
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se eliminó el odontólogo con id = " + id);
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoAActualizar = odontologoService.buscarOdontologoXId(odontologo.getId());
        if (odontologoAActualizar.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("El odontologo con paciente id = "+ odontologo.getId() +  " fue actualizado.");
        }
        else {
            return ResponseEntity.badRequest().body("No se puede actualizar un odontologo que no existe en la base de datos");
        }
    }


}
