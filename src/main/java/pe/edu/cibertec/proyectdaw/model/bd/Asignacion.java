package pe.edu.cibertec.proyectdaw.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer asignacionid;
    @ManyToOne
    @JoinColumn(name = "proyectoid")
    private Proyecto proyecto;
    @ManyToOne
    @JoinColumn(name = "planoid")
    private Plano plano;
    @ManyToOne
    @JoinColumn(name = "empleadoid")
    private Empleado empleado;
}
