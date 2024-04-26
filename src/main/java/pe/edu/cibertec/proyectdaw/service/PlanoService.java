package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Plano;
import pe.edu.cibertec.proyectdaw.repository.PlanoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class PlanoService implements IPlanoService{

    private PlanoRepository planoRepository;

    @Override
    public List<Plano> listarPlano() {
        return planoRepository.findAll();
    }
}
