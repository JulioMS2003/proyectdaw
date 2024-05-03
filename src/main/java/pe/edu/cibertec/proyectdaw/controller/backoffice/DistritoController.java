package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Distrito;
import pe.edu.cibertec.proyectdaw.model.dto.request.DistritoRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IDistritoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/distrito")
public class DistritoController {

    private IDistritoService iDistritoService;

    @GetMapping("")
    public String viewDistrito(Model model){
        model.addAttribute("listaDistritos",
                iDistritoService.listarTodoOrdenadosPorNombreAsc());
        return "backoffice/distrito/viewdistrito";
    }
    @GetMapping("/lista")
    @ResponseBody
    public List<Distrito> listarDistritos(){ return iDistritoService.listarTodoOrdenadosPorNombreAsc();}

    @RequestMapping(value="/guardar", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public ResultadoResponse guardarDistrito(@RequestBody DistritoRequest distritoRequest){
        String mensaje = "Distito guardado";
        boolean respuesta = true;
        try {
            iDistritoService.guardarDistrito(distritoRequest);
        }catch (Exception ex){
            mensaje = "Error:"+ ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public  ResultadoResponse eliminarDistrito(@PathVariable("id") Integer distritoid){
        String mensaje = "Distrito eliiminado";
        boolean respuesta = true;
        try{
            iDistritoService.eliminarDistrito(distritoid);
        }catch (Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();}
}
