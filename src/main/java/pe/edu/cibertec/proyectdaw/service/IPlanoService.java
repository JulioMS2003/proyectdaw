package pe.edu.cibertec.proyectdaw.service;

import pe.edu.cibertec.proyectdaw.model.bd.Plano;
import pe.edu.cibertec.proyectdaw.model.dto.request.PlanoRequest;

import java.util.List;


public interface IPlanoService {

    Plano obtenerPlanoPorId(Integer planoid);
    List<Plano> listarPlano();
    void guardarPlano(PlanoRequest planoRequest) throws Exception;
    List<Plano> listarTodasOrdenadasPorIdAsc();
    List<Plano> listarTodasPorDistritoIdOrdenadasPorNombreAsc(Integer distritoid);
    void eliminarPlano(Integer Planoid);
}
