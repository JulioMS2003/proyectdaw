package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Distrito;
import pe.edu.cibertec.proyectdaw.model.dto.request.DistritoRequest;

import java.util.List;

public interface IDistritoService {
    void guardarDistrito(DistritoRequest distritoRequest) throws Exception;
    List<Distrito> listarDistrito();
    List<Distrito> listarTodoOrdenadosPorNombreAsc();
    List<Distrito> listarTodosPorProvIdOrdenadosPorNombreAsc(Integer provinciaid);
    void eliminarDistrito(Integer distritoid);

}
