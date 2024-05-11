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

import java.util.List;

@Service
@AllArgsConstructor
public class AsignacionService implements IAsignacionService {

    private AsignacionRepository asignacionRepository;
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Asignacion> buscarAsignacionesPorProyecto(Integer proyectoid) {
        return asignacionRepository.findAllByProyectoId(proyectoid);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class})
    public void registrarEmpleadosEnAsignaciones(AsignacionRequest[] asignacionRequests) throws Exception {
        for(AsignacionRequest asignacionRequest: asignacionRequests) {
            Asignacion asignacion = new Asignacion();
            asignacion.setAsignacionid(asignacionRequest.getAsignacionid());
            Proyecto proyecto = new Proyecto();
            proyecto.setProyectoid(asignacionRequest.getProyectoid());
            asignacion.setProyecto(proyecto);
            Plano plano = new Plano();
            plano.setPlanoid(asignacionRequest.getPlanoid());
            asignacion.setPlano(plano);
            Empleado empleado = empleadoRepository.findById(asignacionRequest.getEmpleadoid()).orElse(null);
            if(empleado == null)
                throw new Exception("Plano '" + plano.getPlanoid() + "' no tiene un empleado asignado");
            empleado.setDisponible(false);
            empleadoRepository.save(empleado);
            asignacion.setEmpleado(empleado);
            asignacionRepository.save(asignacion);
        }
    }
}
