package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Asignacion;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Integer> {
}