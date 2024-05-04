package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService implements IUsuarioService{

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();    }

    @Override
    public List<Usuario> listarUsuariosOrdenadosPorApellidos() {
        return usuarioRepository.findAllByOrderByApeusuario();
    }

    @Override
    public Usuario obtenerPorId(Integer usuarioid) {
        Usuario usuario = null;
        Optional<Usuario> optional = usuarioRepository.findById(usuarioid);
        if(optional.isPresent())
            usuario = optional.get();
        return usuario;
    }

    @Override
    public Usuario obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarPassword(String nuevaClave, String username) {
        String encriptado = bCryptPasswordEncoder.encode(nuevaClave);
        usuarioRepository.actualizarPassword(encriptado, username);
    }
}
