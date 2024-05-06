package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Plano;

import java.util.List;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {

    List<Plano> findAllByOrderByPlanoid();

    @Query(value = "Select P From Plano P Where P.distrito.distritoid=:distritoid")
    List<Plano> findAllByDistritoidOrderByNomdist(@Param("distritoid") Integer distritoid);

}
