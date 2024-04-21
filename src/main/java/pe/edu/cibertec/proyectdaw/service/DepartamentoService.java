package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Departamento;
import pe.edu.cibertec.proyectdaw.repository.DepartamentoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class DepartamentoService implements IDepartamentoService {

    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Departamento> listarDepartamento() {
        return departamentoRepository.findAll();
    }
}
