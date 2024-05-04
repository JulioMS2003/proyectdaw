package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Empleado;
import pe.edu.cibertec.proyectdaw.model.dto.request.EmpleadoRequest;

import java.util.List;

public interface IEmpleadoService {
    void guardarEmpleado(EmpleadoRequest empleadoRequest) throws Exception;
    Empleado buscarEmpleadoPorId(Integer empleadoid);
    List<Empleado> listarEmpleado();
    List<Empleado> listarEmpleadosOrdenadosPorApellido();
}
