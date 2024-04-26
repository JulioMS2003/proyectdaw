package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Distrito;
import pe.edu.cibertec.proyectdaw.repository.DistritoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class DistritoService implements IDistritoService{

    private DistritoRepository distritoRepository;

    @Override
    public List<Distrito> listarDistrito() {
        return distritoRepository.findAll();
    }
}
