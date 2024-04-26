package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Proyecto;
import pe.edu.cibertec.proyectdaw.repository.ProyectoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProyectoService implements IProyectoService{

    private ProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> listarProyecto() {
        return proyectoRepository.findAll();
    }
}
