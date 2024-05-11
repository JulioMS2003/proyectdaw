package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Asignacion;
import pe.edu.cibertec.proyectdaw.model.dto.request.AsignacionRequest;

import java.util.List;

public interface IAsignacionService {

    List<Asignacion> buscarAsignacionesPorProyecto(Integer proyectoid);
    void registrarEmpleadosEnAsignaciones(AsignacionRequest[] asignacionRequests) throws Exception;
}
