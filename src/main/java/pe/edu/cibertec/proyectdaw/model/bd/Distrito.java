package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Cleanup;
import lombok.Data;

@Data
@Entity
@Table(name="distrito")
public class Distrito {

    @Id
    private Integer distritoid;
    @Column(name="nomdist")
    private String nomdist;
    @Column(name="provinciaid")
    private Integer provinciaid;

}
