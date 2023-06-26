package ApiRestClinica.clase36.controller;

import org.springframework.web.bind.annotation.*;

//va controller porque va a trabajar con vistas
//@Controller
@RestController
//va request -solicitud- mapping -mapear- porque le vamos a decir que se mapee a esta ruta
@RequestMapping("/odontologos")
public class OdontologoController {
    /*
    private OdontologoService odontologoService;
    @Autowired

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

//    @GetMapping
//    //usa Model para pasarlo a la vista
//    // y el parametro que va a venir de la url
//    public String buscarOdontologoXId(Model model, @RequestParam("id") Integer id) {
//        Odontologo odontologBuscado = odontologoService.buscarOdontologoXId(id);
//        model.addAttribute("nombre", odontologBuscado.getNombre());
//        model.addAttribute("apellido", odontologBuscado.getApellido());
//        model.addAttribute("matricula", odontologBuscado.getMatricula());
//        //como return mandas el nombre de la vista
//        return "busOdontologo";
//    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos() {
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }


     */


}
