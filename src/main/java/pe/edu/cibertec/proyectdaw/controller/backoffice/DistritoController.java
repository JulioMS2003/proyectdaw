package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.service.IDistritoService;

@AllArgsConstructor
@Controller
@RequestMapping("/distrito")
public class DistritoController {

    private IDistritoService iDistritoService;

    @GetMapping("")
    public String viewDistrito(Model model){
        model.addAttribute("distrito",
                iDistritoService.listarDistrito());
        return "backoffice/distrito/distrito";
    }

}
