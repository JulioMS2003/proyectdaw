package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="provincia")
public class Provincia {

    @Id
    private Integer provinciaid;
    @Column(name="nomprov")
    private String nomprov;
    @Column(name="departamentoid")
    private Integer departamentoid;

}
