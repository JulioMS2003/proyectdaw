package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Provincia;
import pe.edu.cibertec.proyectdaw.model.dto.request.ProvinciaRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IProvinciaService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/provincia")
public class ProvinciaCrontroller {

    private IProvinciaService iProvinciaService;

    @GetMapping("")
    public String viewProvincia(Model model) {
        model.addAttribute("listaProvincias",
                iProvinciaService.listarProvincia());
        return "backoffice/provincia/viewprovincia";
    }


    @GetMapping("/lista")
    @ResponseBody
    public List<Provincia> listarProvincias(){
        return iProvinciaService.listarTodasOrdenadasPorNombresAsc();
    }
    @RequestMapping(value="/guardar",method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public ResultadoResponse guardarProvincia(@RequestBody ProvinciaRequest provinciaRequest){
        String mensaje = "Provincia guardada";
        boolean respuesta = true;
        try{
            iProvinciaService.guardarProvincia(provinciaRequest);
        }catch (Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return  ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResultadoResponse eliminarProvincia(@PathVariable("id") Integer departamentoid){
        String mensaje = "Provincia eliminada";
        boolean respuesta = true;
        try{
            iProvinciaService.eliminarProvincia(departamentoid);
        }catch (Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return  ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
