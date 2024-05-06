package pe.edu.cibertec.proyectdaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Empresa;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    List<Empresa> findAllByOrderByNomempresa();
}
