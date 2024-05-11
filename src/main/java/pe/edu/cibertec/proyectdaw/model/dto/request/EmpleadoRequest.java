package pe.edu.cibertec.proyectdaw.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmpleadoRequest {
    private Integer empleadoid;
    private String nomemp;
    private String apeemp;
    private Boolean estado;
    private Boolean disponible;
    private Date fecnac;
    private String email;
    private String telefono;
    private String direccion;
    private Integer distritoid;
}
