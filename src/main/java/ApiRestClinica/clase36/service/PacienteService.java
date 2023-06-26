package ApiRestClinica.clase36.service;

import ApiRestClinica.clase36.model.Paciente;
import ApiRestClinica.clase36.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id) {

        pacienteRepository.deleteById(id);
    }

    public void actualizarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodosPacientes() {
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPacienteByEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }
}
