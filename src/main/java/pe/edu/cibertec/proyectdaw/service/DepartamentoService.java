package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Departamento;
import pe.edu.cibertec.proyectdaw.model.dto.request.DepartamentoRequest;
import pe.edu.cibertec.proyectdaw.repository.DepartamentoRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartamentoService implements IDepartamentoService {

    private DepartamentoRepository departamentoRepository;

    @Override
    public Departamento obtenerPorId(Integer departamentoid) {
        Departamento departamento = null;
        Optional<Departamento> optional = departamentoRepository.findById(departamentoid);
        if(optional.isPresent())
            departamento = optional.get();
        return departamento;
    }

    @Override
    public void registrarDepartamento(DepartamentoRequest departamentoRequest) throws Exception {
        departamentoRequest.setNomdepa(departamentoRequest.getNomdepa().trim());
        if(departamentoRequest.getNomdepa() == null || departamentoRequest.getNomdepa().isEmpty()){
            throw new Exception("Ingresar nombre");
        }
        if(departamentoRequest.getNomdepa().length() > 100) {
            throw new Exception("Nombre demasiado largo (Máximo 100 caracteres)");
        }
        Departamento departamento = new Departamento();
        if(departamentoRequest.getDepartamentoid() > 0)
            departamento.setDepartamentoid(departamentoRequest.getDepartamentoid());
        departamento.setNomdepa(departamentoRequest.getNomdepa());
        this.registrarDepartamento(departamento);
    }

    @Override
    public void registrarDepartamento(Departamento departamento) {
        departamentoRepository.save(departamento);
    }

    @Override
    public List<Departamento> listarDepartamento() {
        return departamentoRepository.findAll();
    }

    @Override
    public List<Departamento> listarDepartamentosOrdenadosPorNombreAsc() {
        return departamentoRepository.findAllByOrderByNomdepa();
    }

    @Override
    public void eliminarDepartamento(Integer departamentoid) {
        departamentoRepository.deleteById(departamentoid);
    }
}
