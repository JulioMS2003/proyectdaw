package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;

@Data
@Entity
@Table(name="empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empresaid;
    @Column(name="nomempresa")
    private String nomempresa;
    @Column(name="ruc")
    private String ruc;
    @Column(name="activo")
    private Boolean activo;
}
