package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.service.IUsuarioService;

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

    @GetMapping("/list")
    @ResponseBody
    public List<Usuario> listarUsuario(){
        return iUsuarioService.listarUsuario();
    }
    
}
