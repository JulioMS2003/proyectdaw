package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Empresa;
import pe.edu.cibertec.proyectdaw.repository.EmpresaRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class EmpresaService implements IEmpresaService{

    private EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> listarEmpresa() {
        return empresaRepository.findAll();
    }
}
