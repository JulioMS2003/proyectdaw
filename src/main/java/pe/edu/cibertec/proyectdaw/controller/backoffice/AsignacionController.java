package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.proyectdaw.model.bd.Asignacion;
import pe.edu.cibertec.proyectdaw.service.IAsignacionService;

import java.util.List;

@Controller
@RequestMapping("/asignacion")
@AllArgsConstructor
public class AsignacionController {

    private IAsignacionService iAsignacionService;

    @GetMapping("/buscar/{proyectoid}")
    @ResponseBody
    public List<Asignacion> buscarAsignacionesPorProyecto(@PathVariable("proyectoid") Integer proyectoid){
        return iAsignacionService.buscarAsignacionesPorProyecto(proyectoid);
    }
}
