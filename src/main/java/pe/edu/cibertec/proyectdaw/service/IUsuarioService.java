package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.model.dto.request.UsuarioRequest;

import java.util.Date;
import java.util.List;

public interface IUsuarioService {

    void registrarNuevoUsuario(UsuarioRequest usuarioRequest) throws Exception;
    List<Usuario> listarUsuario();
    List<Usuario> listarUsuariosOrdenadosPorApellidos();
    Usuario obtenerPorId(Integer usuarioid);
    Usuario obtenerPorUsername(String username);
    void registrarUsuario(Usuario usuario);
    void actualizarPassword(String nuevaClave, String username);
    void actualizarUltimoLogin(Date ultimologin, String username);
}
