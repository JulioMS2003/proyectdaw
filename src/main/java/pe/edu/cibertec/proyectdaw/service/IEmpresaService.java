package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Empresa;

import java.util.List;

public interface IEmpresaService{

    List<Empresa> listarEmpresa();
    List<Empresa> listarEmpresasOrdenadasPorNombreAsc();
}
