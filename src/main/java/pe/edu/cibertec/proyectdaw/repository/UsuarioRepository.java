package pe.edu.cibertec.proyectdaw.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Transactional
    @Modifying
    @Query(value = "Update Usuario Set clave=:clave Where usuarioid=:usuarioid",
            nativeQuery = true)
    void actualizarPassword(@Param("clave") String clave, @Param("usuarioid") String usuarioid);
}
