package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Asignacion;
import pe.edu.cibertec.proyectdaw.model.dto.request.AsignacionRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IAsignacionService;

import java.util.Arrays;
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

    @PutMapping("/asignar-empleado")
    @ResponseBody
    public ResultadoResponse asignarEmpleados(@RequestBody AsignacionRequest[] asignacionRequests) {
        String mensaje = "Planos asignados";
        boolean respuesta = true;
        try{
            iAsignacionService.registrarEmpleadosEnAsignaciones(asignacionRequests);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
