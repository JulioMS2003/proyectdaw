package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.service.IProvinciaService;

@AllArgsConstructor
@Controller
@RequestMapping("/provincia")
public class ProvinciaCrontroller {

    private IProvinciaService iProvinciaService;

    @GetMapping("")
    public String viewProvincia(Model model) {
        model.addAttribute("provincia",
                iProvinciaService.listarProvincia());
        return "backoffice/provincia/viewprovincia";
    }
}
