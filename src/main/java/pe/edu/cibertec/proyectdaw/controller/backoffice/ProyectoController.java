package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.service.IDepartamentoService;
import pe.edu.cibertec.proyectdaw.service.IEmpresaService;
import pe.edu.cibertec.proyectdaw.service.IProyectoService;

@AllArgsConstructor
@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

    private IProyectoService iProyectoService;
    private IEmpresaService iEmpresaService;
    private IDepartamentoService iDepartamentoService;
    @GetMapping("")
    public String generarProyecto(Model model){
        model.addAttribute("listaProyectos",
                iProyectoService.listarProyectos());
        return "backoffice/proyecto/generarproyecto";
    }

    @GetMapping("/nuevo")
    public String nuevoProyecto(Model model) {
        model.addAttribute("listaEmpresas",
                iEmpresaService.listarEmpresasOrdenadasPorNombreAsc());
        model.addAttribute("listaDepartamentos",
                iDepartamentoService.listarDepartamentosOrdenadosPorNombreAsc());
        return "backoffice/proyecto/nuevoproyecto";
    }
}
