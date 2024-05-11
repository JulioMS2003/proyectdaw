package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Asignacion;

import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Integer> {

    @Query("Select A From Asignacion A Where A.proyecto.proyectoid=:proyectoid")
    List<Asignacion> findAllByProyectoId(@Param("proyectoid") Integer proyectoid);
    @Query("Select A From Asignacion A Where A.empleado.empleadoid=:empleadoid")
    List<Asignacion> findAllByEmpleadoId(@Param("empleadoid") Integer empleadoid);
}
