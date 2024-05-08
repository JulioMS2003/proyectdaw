package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Departamento;
import pe.edu.cibertec.proyectdaw.model.bd.Empleado;
import pe.edu.cibertec.proyectdaw.model.bd.Plano;
import pe.edu.cibertec.proyectdaw.model.dto.request.DepartamentoRequest;
import pe.edu.cibertec.proyectdaw.model.dto.request.PlanoRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IPlanoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/plano")
public class PlanoController {

    private IPlanoService iPlanoService;

    @GetMapping("")
    public String viewPlano(Model model){
        model.addAttribute("listaPlano",
                iPlanoService.listarPlano());
        return "backoffice/plano/viewplano";
    }

    @GetMapping("/buscar/{planoid}")
    @ResponseBody
    public Plano encontrarPlano(@PathVariable("planoid") String planoid) {
        return iPlanoService.obtenerPlanoPorId(planoid);
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<Plano> listarPlanos(){ return iPlanoService.listarTodasOrdenadasPorIdAsc();}

    @GetMapping("/lista/{distritoid}")
    @ResponseBody
        public List<Plano> listarPlanoPorDistrito(@PathVariable("distritoid") Integer distritoid){
            return iPlanoService.listarTodasPorDistritoIdOrdenadasPorNombreAsc(distritoid);
        }

    @RequestMapping(value = "/guardar", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public ResultadoResponse guardarPlano(@RequestBody PlanoRequest planoRequest){
        String mensaje = "Plano guardado";
        boolean respuesta = true;
        try{
            iPlanoService.guardarPlano(planoRequest);
        } catch (Exception ex){
            mensaje = "Error: " + ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @PutMapping("/actualizacion")
    @ResponseBody
    public ResultadoResponse actualizarPlano(@RequestBody PlanoRequest planoRequest) {
        String mensaje = "Plano actualizado con éxito";
        boolean respuesta = true;
        try{
            iPlanoService.guardarPlano(planoRequest);
        } catch(Exception ex){
            mensaje = "Error: " + ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResultadoResponse eliminarPlano(@PathVariable("id") String planoid){
        String mensaje = "Plano eliminado";
        boolean respuesta = true;
        try{
            iPlanoService.eliminarPlano(planoid);
        }catch (Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return  ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @GetMapping("/obtener/{id}")
    @ResponseBody
    public Plano obtenerPorId(@PathVariable("id") String planoid) {
        return iPlanoService.obtenerPlanoPorId(planoid);
    }

}
