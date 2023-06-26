package ApiRestClinica.clase36.service;

import ApiRestClinica.clase36.exception.ResourceNotFoundException;
import ApiRestClinica.clase36.model.Odontologo;
import ApiRestClinica.clase36.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }
    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        //el se va a encargar de manejar la excepcion
        Optional<Odontologo> odontologoAEliminar = buscarOdontologoXId(id);
        if (odontologoAEliminar.isPresent()){
            odontologoRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("El odontologo a eliminar no existe " +
                    "en la base de datos, se intentó encontrar sin éxito el id= " + id);
        }
        odontologoRepository.deleteById(id);
    }
    public Optional<Odontologo> buscarOdontologoXId(Long id){
        return odontologoRepository.findById(id);
    }


}
