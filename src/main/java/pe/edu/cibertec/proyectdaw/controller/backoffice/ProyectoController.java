package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.service.IProyectoService;

@AllArgsConstructor
@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

    private IProyectoService iProyectoService;
    @GetMapping("")
    public String generarProyecto(Model model){
        model.addAttribute("proyecto",
                iProyectoService.listarProyecto());
        return "backoffice/proyecto/generarproyecto";
    }

}
