package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listarUsuario();
    List<Usuario> listarUsuariosOrdenadosPorApellidos();
    Usuario obtenerPorId(Integer usuarioid);
    Usuario obtenerPorUsername(String username);
    void registrarUsuario(Usuario usuario);
    void actualizarPassword(String nuevaClave, String username);
}
