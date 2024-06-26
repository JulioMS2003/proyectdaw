package pe.edu.cibertec.proyectdaw.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectdaw.model.bd.Rol;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername(String username);
    List<Usuario> findAllByOrderByApeusuario();
    @Transactional
    @Modifying
    @Query(value = "Update Usuario Set password=:password Where username=:username",
            nativeQuery = true)
    void actualizarPassword(@Param("password") String password, @Param("username") String username);
    @Transactional
    @Modifying
    @Query(value = "Update Usuario Set ultimologin=:ultimologin Where username=:username")
    void actualizarUltimoLogin(@Param("ultimologin") Date ultimologin, @Param("username") String username);
    @Query(value = "Call PaginacionUsuarios(:skip)", nativeQuery = true)
    List<Usuario> paginacionUsuarios(@Param("skip") Integer skip);
}
