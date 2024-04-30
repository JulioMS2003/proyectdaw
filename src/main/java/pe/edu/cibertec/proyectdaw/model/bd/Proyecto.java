package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proyectoid;
    @Column(name="fecinicio")
    private Date fecinicio;
    @Column(name="fecfin")
    private Date fecfin;
    @Column(name="estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name="empresaid")
    private Empresa empresa;
}
