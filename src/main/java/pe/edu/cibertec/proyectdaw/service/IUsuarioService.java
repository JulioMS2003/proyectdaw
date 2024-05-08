package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Rol;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.model.dto.request.UsuarioRequest;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IUsuarioService {

    void registrarNuevoUsuario(UsuarioRequest usuarioRequest) throws Exception;
    List<Usuario> listarUsuario();
    List<Usuario> listarUsuariosOrdenadosPorApellidos();
    List<Usuario> paginacionUsuarios(Integer nropagina);
    Usuario obtenerPorId(Integer usuarioid);
    Usuario obtenerPorUsername(String username);
    void registrarUsuario(Usuario usuario);
    void actualizarDatosUsuario1(UsuarioRequest usuarioRequest) throws Exception;
    void actualizarPassword(String nuevaClave, String username);
    void actualizarUltimoLogin(Date ultimologin, String username);
}
