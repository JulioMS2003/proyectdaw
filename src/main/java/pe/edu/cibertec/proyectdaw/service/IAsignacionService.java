package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Asignacion;

import java.util.List;

public interface IAsignacionService {

    List<Asignacion> buscarAsignacionesPorProyecto(Integer proyectoid);
}
