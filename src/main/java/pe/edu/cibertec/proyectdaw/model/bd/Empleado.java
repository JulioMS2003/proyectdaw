package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empleadoid;
    @Column(name="nomemp")
    private String nomemp;
    @Column(name="apeemp")
    private String apeemp;
    @Column(name="estado")
    private Boolean estado;
    @Column(name = "disponible")
    private Boolean disponible;
    @Column(name="fecnac")
    @Temporal(TemporalType.DATE)
    private Date fecnac;
    @Column(name="email")
    private String email;
    @Column(name="telefono")
    private String telefono;
    @Column(name="direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name="distritoid")
    private Distrito distrito;

}
