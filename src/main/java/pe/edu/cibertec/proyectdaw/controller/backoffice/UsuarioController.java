package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.model.dto.request.UsuarioRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IUsuarioService;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private IUsuarioService iUsuarioService;
    @GetMapping("")
    public String viewUsuario(Model model){
        model.addAttribute("listaUsuarios",
                iUsuarioService.listarUsuariosOrdenadosPorApellidos());
        return "backoffice/usuario/viewusuario";
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<Usuario> listarUsuario(){
        return iUsuarioService.listarUsuariosOrdenadosPorApellidos();
    }

    @PostMapping("/guardar/nuevo")
    @ResponseBody
    public ResultadoResponse guardarNuevoUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        String mensaje = "Nuevo Usuario guardado";
        boolean respuesta = true;
        try{
            iUsuarioService.registrarNuevoUsuario(usuarioRequest);
        } catch(Exception ex) {
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @GetMapping("/{usuarioid}")
    @ResponseBody
    public Usuario obtenerPorId(@PathVariable("usuarioid") Integer usuarioid) {
        return iUsuarioService.obtenerPorId(usuarioid);
    }
}
