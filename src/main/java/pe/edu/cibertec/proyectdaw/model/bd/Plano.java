package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="plano")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planoid;
    @Column(name="estado")
    private String estado;
    @ManyToOne
    @JoinColumn(name="distritoid")
    private Distrito distrito;

}
