package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Distrito;

import java.util.List;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    List<Distrito> findAllByOrderByNomdist();

    @Query("Select D From Distrito D Where D.provincia.provinciaid=:provinciaid")
    List<Distrito> findAllByProvIdOrderByNomdist(@Param("provinciaid") Integer provinciaid);

    @Query(value = "Call PaginacionDistritos(:skip)", nativeQuery = true)
    List<Distrito> paginacionDistritos(@Param("skip") Integer skip);
}
