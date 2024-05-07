package pe.edu.cibertec.proyectdaw.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProyectoRequest {
    private Integer proyectoid;
    private Integer empresaid;
    private Integer distritoid;
    private Date fecinicio;
    private String[] planos;
}
