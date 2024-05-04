package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Empleado;
import pe.edu.cibertec.proyectdaw.model.dto.request.EmpleadoRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IEmpleadoService;

import java.util.List;

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

    @GetMapping("/lista")
    @ResponseBody
    public List<Empleado> listarEmpleados(){
        return iEmpleadoService.listarEmpleadosOrdenadosPorApellido();
    }

    @GetMapping("/buscar/{empid}")
    @ResponseBody
    public Empleado encontrarEmpleado(@PathVariable("empid") Integer empid) {
        return iEmpleadoService.buscarEmpleadoPorId(empid);
    }

    @RequestMapping(value = "/guardar", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public ResultadoResponse guardarEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {
        String mensaje = "Empleado guardado";
        boolean respuesta = true;
        try{
            iEmpleadoService.guardarEmpleado(empleadoRequest);
        } catch(Exception ex){
            mensaje = "Error: " + ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
