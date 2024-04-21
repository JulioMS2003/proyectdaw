package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Timer;

@Data
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    private Integer usuarioid;
    @Column(name="clave")
    private String clave;
    @Column(name="nomusuario")
    private String nomusuario;
    @Column(name="apeusuario")
    private String apeusuario;
    @Column(name="rolid")
    private Integer rolid;
    @Column(name="estado")
    private Boolean estado;
    @Column(name="ultimo_login")
    private Timestamp ultimologin;

}
