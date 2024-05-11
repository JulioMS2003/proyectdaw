package pe.edu.cibertec.proyectdaw.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignacionRequest {
    private Integer asignacionid;
    private Integer proyectoid;
    private String planoid;
    private Integer empleadoid;
}
