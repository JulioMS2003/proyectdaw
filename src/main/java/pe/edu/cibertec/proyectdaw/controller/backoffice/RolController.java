package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.proyectdaw.model.bd.Rol;
import pe.edu.cibertec.proyectdaw.service.IRolService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/rol")
public class RolController {

    private IRolService iRolService;

    @GetMapping("/lista")
    @ResponseBody
    public List<Rol> listRol(){
        return iRolService.listarRolesOrdenadosPorNombre();
    }

}
