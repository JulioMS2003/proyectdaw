package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="plano")
public class Plano {

    @Id
    private Integer planoid;
    @Column(name="estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name="distritoid")
    private Distrito distrito;

}
