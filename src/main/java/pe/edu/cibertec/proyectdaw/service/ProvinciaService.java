package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Provincia;
import pe.edu.cibertec.proyectdaw.repository.ProvinciaRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProvinciaService implements IProvinciaService {

    private ProvinciaRepository provinciaRepository;

    @Override
    public List<Provincia> listarProvincia() {
        return provinciaRepository.findAll();
    }
}
