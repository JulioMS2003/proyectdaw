package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="proyecto")
public class Proyecto {

    @Id
    private Integer proyectoid;
    @Column(name="empresaid")
    private Integer empresaid;
    @Column(name="fecinicio")
    private Date fecinicio;
    @Column(name="fecfin")
    private Date fecfin;
    @Column(name="estado")
    private Boolean estado;

}
