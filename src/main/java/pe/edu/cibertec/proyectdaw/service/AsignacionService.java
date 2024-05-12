package pe.edu.cibertec.proyectdaw.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Asignacion;
import pe.edu.cibertec.proyectdaw.model.bd.Empleado;
import pe.edu.cibertec.proyectdaw.model.bd.Plano;
import pe.edu.cibertec.proyectdaw.model.bd.Proyecto;
import pe.edu.cibertec.proyectdaw.model.dto.request.AsignacionRequest;
import pe.edu.cibertec.proyectdaw.repository.AsignacionRepository;
import pe.edu.cibertec.proyectdaw.repository.EmpleadoRepository;
import pe.edu.cibertec.proyectdaw.repository.ProyectoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AsignacionService implements IAsignacionService {

    private AsignacionRepository asignacionRepository;
    private EmpleadoRepository empleadoRepository;
    private ProyectoRepository proyectoRepository;

    @Override
    public List<Asignacion> buscarAsignacionesPorProyecto(Integer proyectoid) {
        return asignacionRepository.findAllByProyectoId(proyectoid);
    }

    @Override
    public List<Asignacion> buscarAsignacionesPorEmpleado(Integer empleadoid) {
        return asignacionRepository.findAllByEmpleadoId(empleadoid);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class})
    public void registrarEmpleadosEnAsignaciones(AsignacionRequest[] asignacionRequests) throws Exception {
        Proyecto proyecto = proyectoRepository.findById(asignacionRequests[0].getProyectoid()).orElse(null);

        for(AsignacionRequest asignacionRequest: asignacionRequests) {
            Asignacion asignacion = new Asignacion();
            asignacion.setAsignacionid(asignacionRequest.getAsignacionid());
            asignacion.setProyecto(proyecto);
            Plano plano = new Plano();
            plano.setPlanoid(asignacionRequest.getPlanoid());
            asignacion.setPlano(plano);
            Empleado empleado = empleadoRepository.findById(asignacionRequest.getEmpleadoid()).orElse(null);
            if(empleado == null)
                throw new Exception("Plano '" + plano.getPlanoid() + "' no tiene un empleado asignado");
            if(!empleado.getDisponible())
                throw new Exception("Solo se puede asignar un plano por empleado");
            empleado.setDisponible(false);
            empleadoRepository.save(empleado);
            asignacion.setEmpleado(empleado);
            asignacionRepository.save(asignacion);
        }

        proyecto.setEstado("A");
        proyectoRepository.save(proyecto);
    }
}
