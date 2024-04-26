package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.service.IPlanoService;

@AllArgsConstructor
@Controller
@RequestMapping("/plano")
public class PlanoController {

    private IPlanoService iPlanoService;

    @GetMapping("")
    public String viewPlano(Model model){
        model.addAttribute("plano",
                iPlanoService.listarPlano());
        return "backoffice/plano/plano";
    }

}
