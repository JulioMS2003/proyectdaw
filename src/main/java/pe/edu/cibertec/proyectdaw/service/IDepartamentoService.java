package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Departamento;
import pe.edu.cibertec.proyectdaw.model.dto.request.DepartamentoRequest;

import java.util.List;

public interface IDepartamentoService{

    Departamento obtenerPorId(Integer departamentoid);
    void registrarDepartamento(DepartamentoRequest departamentoRequest) throws Exception;
    void registrarDepartamento(Departamento departamento);
    List<Departamento> listarDepartamento();
    List<Departamento> listarDepartamentosOrdenadosPorNombreAsc();
}
