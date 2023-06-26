package ApiRestClinica.clase36.security;

import ApiRestClinica.clase36.model.Usuario;
import ApiRestClinica.clase36.model.UsuarioRole;
import ApiRestClinica.clase36.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //cargar un usuario para probar
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passCifrada = cifrador.encode("programacion");
        Usuario usuario = new Usuario("Vanina", "Vanina", "vanina@gmail.com",
                passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
    }
}
