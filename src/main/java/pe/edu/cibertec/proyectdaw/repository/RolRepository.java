package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Rol;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    List<Rol> findAllByOrderByNomrol();
}
