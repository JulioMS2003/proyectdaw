package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Empleado;

import java.util.List;

public interface IEmpleadoService {
    Empleado buscarEmpleadoPorId(Integer empleadoid);
    List<Empleado> listarEmpleado();
    List<Empleado> listarEmpleadosOrdenadosPorApellido();
}
