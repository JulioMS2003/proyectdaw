package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Asignacion;
import pe.edu.cibertec.proyectdaw.repository.AsignacionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AsignacionService implements IAsignacionService {

    private AsignacionRepository asignacionRepository;
    @Override
    public List<Asignacion> buscarAsignacionesPorProyecto(Integer proyectoid) {
        return asignacionRepository.findAllByProyectoId(proyectoid);
    }
}
