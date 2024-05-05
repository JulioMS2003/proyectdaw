package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

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
    @Column(name="ultimologin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimologin;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "Usuario_Rol",
    joinColumns = @JoinColumn(name = "usuarioid"),
    inverseJoinColumns = @JoinColumn(name = "rolid"))
    private Set<Rol> roles;
}
