package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Empleado;
import pe.edu.cibertec.proyectdaw.repository.EmpleadoRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmpleadoService implements IEmpleadoService{

    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado buscarEmpleadoPorId(Integer empleadoid) {
        Empleado empleado = null;
        Optional<Empleado> optional = empleadoRepository.findById(empleadoid);
        if(optional.isPresent())
            empleado = optional.get();
        return empleado;
    }

    @Override
    public List<Empleado> listarEmpleado() {
        return empleadoRepository.findAll();
    }

    @Override
    public List<Empleado> listarEmpleadosOrdenadosPorApellido() {
        return empleadoRepository.findAllByOrderByApeemp();
    }
}
