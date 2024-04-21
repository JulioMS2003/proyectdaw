package pe.edu.cibertec.proyectdaw.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {

    private Boolean respuesta;
    private String mensaje;

}
