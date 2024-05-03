package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Provincia;
import pe.edu.cibertec.proyectdaw.model.dto.request.ProvinciaRequest;

import java.util.List;

public interface IProvinciaService {
    void guardarProvincia(ProvinciaRequest provinciaRequest) throws Exception;

    List<Provincia> listarProvincia();
    List<Provincia> listarTodasOrdenadasPorNombresAsc();
    List<Provincia> listarTodasPorDepaIdOrdenadasPorNombreAsc(Integer departamentoid);
    void eliminarProvincia(Integer provinciaid);
}
