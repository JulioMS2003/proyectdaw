package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Provincia;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    List<Provincia> findAllByOrderByNomprov();
}
