package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.proyectdaw.model.bd.Empleado;
import pe.edu.cibertec.proyectdaw.service.IEmpleadoService;

@AllArgsConstructor
@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    private IEmpleadoService iEmpleadoService;

    @GetMapping("")
    public String viewEmpleado(Model model){
        model.addAttribute("listaEmpleados",
                iEmpleadoService.listarEmpleadosOrdenadosPorApellido());
        return "backoffice/empleado/viewempleado";
    }

    @GetMapping("/buscar/{empid}")
    @ResponseBody
    public Empleado encontrarEmpleado(@PathVariable("empid") Integer empid) {
        return iEmpleadoService.buscarEmpleadoPorId(empid);
    }
}
