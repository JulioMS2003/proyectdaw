package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Rol;
import pe.edu.cibertec.proyectdaw.repository.RolRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class RolService implements IRolService{

    private RolRepository rolRepository;

    @Override
    public List<Rol> listarRol() {
        return rolRepository.findAll();
    }
}
