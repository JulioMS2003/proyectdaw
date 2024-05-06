package pe.edu.cibertec.proyectdaw.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaRequest {
    private Integer empresaid;
    private String nomempresa;
    private String ruc;
    private Boolean activo;
}
