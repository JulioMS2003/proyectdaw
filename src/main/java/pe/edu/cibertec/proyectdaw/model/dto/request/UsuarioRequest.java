package pe.edu.cibertec.proyectdaw.model.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class UsuarioRequest {

    private Integer usuarioid;
    private String nomusuario;
    private String apeusuario;
    private Boolean activo;
    private int[] idroles;
}
