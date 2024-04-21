package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name="provinciaid")
    private Provincia provincia;

}
