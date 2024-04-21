package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="provincia")
public class Provincia {

    @Id
    private Integer provinciaid;
    @Column(name="nomprov")
    private String nomprov;

    @ManyToOne
    @JoinColumn(name="departamentoid")
    private Departamento departamento;

}
