package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="departamento")
public class Departamento {

    @Id
    private Integer departamentoid;
    @Column(name="nomdepa")
    private String nomdepa;

}
