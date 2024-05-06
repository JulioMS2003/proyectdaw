package pe.edu.cibertec.proyectdaw.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Rol;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.model.dto.request.UsuarioRequest;
import pe.edu.cibertec.proyectdaw.repository.RolRepository;
import pe.edu.cibertec.proyectdaw.repository.UsuarioRepository;

import java.util.*;

@AllArgsConstructor
@Service
public class UsuarioService implements IUsuarioService{

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void registrarNuevoUsuario(UsuarioRequest usuarioRequest) throws Exception {
        usuarioRequest.setNomusuario(usuarioRequest.getNomusuario().trim());
        usuarioRequest.setApeusuario(usuarioRequest.getApeusuario().trim());
        if(usuarioRequest.getNomusuario() == null || usuarioRequest.getNomusuario().isEmpty())
            throw new Exception("Ingresar nombres del nuevo usuario");
        if(usuarioRequest.getNomusuario().length() > 50)
            throw new Exception("Nombres ingresados superan el límite de 50 caracteres");
        if(usuarioRequest.getApeusuario() == null || usuarioRequest.getApeusuario().isEmpty())
            throw new Exception("Ingresar apellidos del nuevo usuario");
        if(usuarioRequest.getApeusuario().length() > 50)
            throw new Exception("Apellidos ingresados superan el límite de 50 caracteres");
        if(usuarioRequest.getIdroles().length == 0)
            throw new Exception("Seleccionar al menos un rol");

        Usuario usuario = new Usuario();
        usuario.setUsername(this.generarNuevoUsername());
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getUsername()));
        usuario.setNomusuario(usuarioRequest.getNomusuario());
        usuario.setApeusuario(usuarioRequest.getApeusuario());
        usuario.setActivo(true);
        Set<Rol> roles = new HashSet<>();
        for(int rolid: usuarioRequest.getIdroles()) {
            Rol rol = rolRepository.findById(rolid).orElse(null);
            roles.add(rol);
        }
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
    }

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
    public void actualizarDatosUsuario1(UsuarioRequest usuarioRequest) throws Exception {
        usuarioRequest.setNomusuario(usuarioRequest.getNomusuario().trim());
        usuarioRequest.setApeusuario(usuarioRequest.getApeusuario().trim());
        if(usuarioRequest.getNomusuario() == null || usuarioRequest.getNomusuario().isEmpty())
            throw new Exception("Ingresar nombres del nuevo usuario");
        if(usuarioRequest.getNomusuario().length() > 50)
            throw new Exception("Nombres ingresados superan el límite de 50 caracteres");
        if(usuarioRequest.getApeusuario() == null || usuarioRequest.getApeusuario().isEmpty())
            throw new Exception("Ingresar apellidos del nuevo usuario");
        if(usuarioRequest.getApeusuario().length() > 50)
            throw new Exception("Apellidos ingresados superan el límite de 50 caracteres");
        if(usuarioRequest.getIdroles().length == 0)
            throw new Exception("Seleccionar al menos un rol");

        Usuario usuario = this.obtenerPorId(usuarioRequest.getUsuarioid());
        usuario.setNomusuario(usuarioRequest.getNomusuario());
        usuario.setApeusuario(usuarioRequest.getApeusuario());
        usuario.setActivo(usuarioRequest.getActivo());
        List<Rol> nuevosRoles = new ArrayList<>();
        for(int rolid: usuarioRequest.getIdroles()) {
            Rol rol = rolRepository.findById(rolid).orElse(null);
            nuevosRoles.add(rol);
        }
        usuario.getRoles().removeIf(rol -> !nuevosRoles.contains(rol));
        for(Rol rol: nuevosRoles) {
                usuario.getRoles().add(rol);
        }
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarPassword(String nuevaClave, String username) {
        String encriptado = bCryptPasswordEncoder.encode(nuevaClave);
        usuarioRepository.actualizarPassword(encriptado, username);
    }

    @Override
    public void actualizarUltimoLogin(Date ultimologin, String username) {
        usuarioRepository.actualizarUltimoLogin(ultimologin, username);
    }

    private String generarNuevoUsername(){
        String username = "usuario";
        do {
            if(username.length() == 11)
                username = username.substring(0, 6);
            int digito = (int) (Math.random() * 10);
            username += digito;
        } while(username.length() != 11 || this.obtenerPorUsername(username) != null);
        return username;
    }
}
