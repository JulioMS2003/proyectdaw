package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.service.IRolService;
import pe.edu.cibertec.proyectdaw.service.IUsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/rol")
public class RolController {

    private IRolService iRolService;
    @GetMapping("/list")
    public String listarRol(Model model){
        model.addAttribute("rol",
                iRolService.listarRol());
        return "backoffice/rol/viewrol";
    }

}
