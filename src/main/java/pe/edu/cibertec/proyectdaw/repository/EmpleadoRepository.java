package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    List<Empleado> findAllByOrderByApeemp();
    @Query(value = "Call PaginacionEmpleados(:skip)", nativeQuery = true)
    List<Empleado> paginacionEmpleados(@Param("skip") Integer skip);
}
