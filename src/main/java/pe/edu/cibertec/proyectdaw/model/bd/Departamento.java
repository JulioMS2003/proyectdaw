package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departamentoid;
    @Column(name="nomdepa")
    private String nomdepa;

}
