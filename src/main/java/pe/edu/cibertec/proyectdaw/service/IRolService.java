package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Rol;

import java.util.List;

public interface IRolService {

    List<Rol> listarRol();
    List<Rol> listarRolesOrdenadosPorNombre();
}
