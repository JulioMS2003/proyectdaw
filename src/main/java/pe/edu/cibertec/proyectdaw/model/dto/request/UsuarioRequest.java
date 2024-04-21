package pe.edu.cibertec.proyectdaw.model.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UsuarioRequest {

    private String usuarioid;
    private String clave;
    private String nomusuario;
    private String apeusuario;
    private Integer rolid;
    private Boolean estado;
    private Timestamp ultimologin;
}
