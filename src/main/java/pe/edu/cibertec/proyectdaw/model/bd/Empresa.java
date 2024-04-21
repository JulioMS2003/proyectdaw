package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Cleanup;
import lombok.Data;

@Data
@Entity
@Table(name="empresa")
public class Empresa {

    @Id
    private Integer empresaid;
    @Column(name="nomempresa")
    private String nomempresa;
    @Column(name="ruc")
    private String ruc;
    @Column(name="estado")
    private Boolean estado;

}
