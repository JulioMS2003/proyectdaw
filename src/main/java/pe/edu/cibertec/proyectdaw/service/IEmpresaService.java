package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Empresa;
import pe.edu.cibertec.proyectdaw.model.dto.request.EmpresaRequest;

import java.util.List;

public interface IEmpresaService{

    void guardarEmpresa(EmpresaRequest empresaRequest) throws Exception;
    List<Empresa> listarEmpresa();
    List<Empresa> listarEmpresasOrdenadasPorNombreAsc();
    List<Empresa> paginacionEmpresas(Integer nropagina);
}
