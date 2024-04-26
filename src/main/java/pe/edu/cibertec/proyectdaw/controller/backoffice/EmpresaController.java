package pe.edu.cibertec.proyectdaw.controller.backoffice;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.service.IEmpresaService;

@AllArgsConstructor
@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    private IEmpresaService iEmpresaService;

    @GetMapping("")
    public String viewEmpresa(Model model){
        model.addAttribute("empresa",
                iEmpresaService.listarEmpresa());
        return  "backoffice/empresa/empresa";
    }
}
