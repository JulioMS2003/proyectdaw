package pe.edu.cibertec.proyectdaw.controller.backoffice;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Rol;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.model.dto.request.UsuarioRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.UsuarioResponse;
import pe.edu.cibertec.proyectdaw.service.IDepartamentoService;
import pe.edu.cibertec.proyectdaw.service.IUsuarioService;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private IUsuarioService iUsuarioService;
    @GetMapping("")
    public String viewUsuario(Model model){
        model.addAttribute("usuario",
                iUsuarioService.listarUsuario());
        return "backoffice/usuario/viewusuario";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Usuario> listarUsuario(){
        return iUsuarioService.listarUsuario();
    }

    /*@PostMapping("/registrar")
    @ResponseBody
    public UsuarioResponse registrarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        String mensaje = "Usuario Registrado Correctamente";
        boolean respuesta = true;

        try{
            Usuario usuario = new Usuario();

            Rol rol = new Rol();
            rol.setRolid(usuarioRequest.getRolid());
            usuario.setRol(rol);
            iUsuarioService.registrarUsuario(usuario);
        }
        catch (Exception ex) {

            mensaje = "Usuario Registrado, Error en la BD.";
            respuesta = false;

        }
        return UsuarioResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }*/
}
