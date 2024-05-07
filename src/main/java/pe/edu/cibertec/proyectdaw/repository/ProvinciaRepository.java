package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Provincia;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    List<Provincia> findAllByOrderByNomprov();

    @Query(value = "Select P From Provincia P Where P.departamento.departamentoid=:departamentoid")
    List<Provincia> findAllByDepaIdOrderByNomprov(@Param("departamentoid") Integer departamentoid);

    @Query(value = "Call PaginacionProvincias(:skip)", nativeQuery = true)
    List<Provincia> paginacionProvincias(@Param("skip") Integer skip);
}
