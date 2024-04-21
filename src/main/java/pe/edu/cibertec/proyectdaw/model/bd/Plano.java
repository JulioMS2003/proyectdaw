package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="plano")
public class Plano {

    @Id
    private Integer planoid;
    @Column(name="distritoid")
    private Integer distritoid;
    @Column(name="estado")
    private Boolean estado;

}
