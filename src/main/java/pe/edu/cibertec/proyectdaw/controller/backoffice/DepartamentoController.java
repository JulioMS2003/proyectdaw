package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.service.IDepartamentoService;

@AllArgsConstructor
@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

    private IDepartamentoService iDepartamentoService;

    @GetMapping("/list")
    public String listarDepartamento(Model model){
        model.addAttribute("departamento",
                iDepartamentoService.listarDepartamento());
        return "backoffice/departamento/viewdepartamento";
    }

}
