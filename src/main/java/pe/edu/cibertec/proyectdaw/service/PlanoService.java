package pe.edu.cibertec.proyectdaw.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Distrito;
import pe.edu.cibertec.proyectdaw.model.bd.Plano;
import pe.edu.cibertec.proyectdaw.model.dto.request.PlanoRequest;
import pe.edu.cibertec.proyectdaw.repository.PlanoRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlanoService implements IPlanoService{

    private PlanoRepository planoRepository;

    @Override
    public Plano obtenerPlanoPorId(String planoid) {
        Plano plano = null;
        Optional<Plano> optional = planoRepository.findById(planoid);
        if(optional.isPresent())
            plano = optional.get();
        return plano;
    }

    @Override
    public List<Plano> listarPlano() {
        return planoRepository.findAll();
    }

    @Override
    public void guardarPlano(PlanoRequest planoRequest) throws Exception {
        if(planoRequest.getDistritoid()==-1)
            throw new Exception("Debe seleccionar un distrito");
        Plano plano = new Plano();
        if(this.obtenerPlanoPorId(planoRequest.getPlanoid()) != null)
            plano.setPlanoid(planoRequest.getPlanoid());
        Distrito distrito = new Distrito();
        distrito.setDistritoid(planoRequest.getDistritoid());
        plano.setDistrito(distrito);
        plano.setEstado(planoRequest.getEstado());
        planoRepository.save(plano);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class})
    public void guardarEstadosPlanos(PlanoRequest[] planoRequests) throws Exception {
        for(PlanoRequest planoRequest: planoRequests) {
            Plano plano = planoRepository.findById(planoRequest.getPlanoid()).orElse(null);
            if(plano == null)
                throw new Exception("No se encontró plano");
            plano.setEstado(planoRequest.getEstado());
            planoRepository.save(plano);
        }
    }

    @Override
    public List<Plano> listarTodasOrdenadasPorIdAsc() {
        return planoRepository.findAllByOrderByPlanoid();
    }

    @Override
    public List<Plano> listarTodasPorDistritoIdOrdenadasPorNombreAsc(Integer distritoid) {
        return planoRepository.findAllByDistritoidOrderByNomdist(distritoid);
    }

    @Override
    public void eliminarPlano(String planoid) {
        planoRepository.deleteById(planoid);
    }
}
