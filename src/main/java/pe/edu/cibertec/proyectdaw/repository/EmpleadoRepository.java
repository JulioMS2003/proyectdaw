package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.proyectdaw.model.bd.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
