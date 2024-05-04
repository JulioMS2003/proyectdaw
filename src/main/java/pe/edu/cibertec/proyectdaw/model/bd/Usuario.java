package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.Timer;

@Data
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioid;
    @Column(name = "username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="nomusuario")
    private String nomusuario;
    @Column(name="apeusuario")
    private String apeusuario;
    @Column(name="activo")
    private Boolean activo;
    @Column(name="ultimo_login")
    private Timestamp ultimologin;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "Usuario_Rol",
    joinColumns = @JoinColumn(name = "usuarioid"),
    inverseJoinColumns = @JoinColumn(name = "rolid"))
    private Set<Rol> roles;
}
