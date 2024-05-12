package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Proyecto;
import pe.edu.cibertec.proyectdaw.model.dto.request.ProyectoRequest;

import java.util.List;

public interface IProyectoService {
    void generarProyecto(ProyectoRequest proyectoRequest) throws Exception;
    Proyecto buscarPorId(Integer proyectoid);
    List<Proyecto> listarProyectos();
    List<Proyecto> listarProyectosOrdenadosPorFechaDeInicio();
    List<Proyecto> paginacionProyectos(Integer nropagina);
    void cancelarProyecto(Integer proyectoid) throws Exception;
    void finalizarProyecto(Integer proyectoid) throws Exception;
}
