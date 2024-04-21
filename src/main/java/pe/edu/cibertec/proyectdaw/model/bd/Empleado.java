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
    private Integer empleadoid;
    @Column(name="nomemp")
    private Integer nomemp;
    @Column(name="apeemp")
    private Integer apeemp;
    @Column(name="estado")
    private Boolean estado;
    @Column(name="fecNac")
    private Date fecNac;
    @Column(name="email")
    private String email;
    @Column(name="telefono")
    private String telefono;
    @Column(name="foto")
    private String foto;
    @Column(name="direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name="distritoid")
    private Distrito distrito;

}
