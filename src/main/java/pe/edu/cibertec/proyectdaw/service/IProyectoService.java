package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Proyecto;
import pe.edu.cibertec.proyectdaw.model.dto.request.ProyectoRequest;

import java.util.List;

public interface IProyectoService {
    void generarProyecto(ProyectoRequest proyectoRequest) throws Exception;
    Proyecto buscarPorId(Integer proyectoid);
    List<Proyecto> listarProyectos();
    void cancelarProyecto(Integer proyectoid);
}
