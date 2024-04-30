package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listarUsuario();
    Usuario obtenerPorId(String usuarioid);
    void registrarUsuario(Usuario usuario);
    void actualizarPassword(String nuevaClave, String usuarioid);
}
