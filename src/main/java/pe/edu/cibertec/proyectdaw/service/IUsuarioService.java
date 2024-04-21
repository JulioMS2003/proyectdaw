package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listarUsuario();

    void registrarUsuario(Usuario usuario);
}
