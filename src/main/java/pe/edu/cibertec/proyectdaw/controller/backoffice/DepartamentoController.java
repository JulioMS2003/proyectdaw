package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Departamento;
import pe.edu.cibertec.proyectdaw.model.dto.request.DepartamentoRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IDepartamentoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

    private IDepartamentoService iDepartamentoService;

    @GetMapping("")
    public String viewDepartamento(Model model){
        model.addAttribute("listaDepartamentos",
                iDepartamentoService.listarDepartamentosOrdenadosPorNombreAsc());
        return "backoffice/departamento/viewdepartamento";
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<Departamento> listarDepartamentos(){
        return iDepartamentoService.listarDepartamentosOrdenadosPorNombreAsc();
    }

    @PostMapping("/registro")
    @ResponseBody
    public ResultadoResponse registrarDepartamento(@RequestBody DepartamentoRequest departamentoRequest) {
        String mensaje = "Departamento guardado";
        boolean respuesta = true;
        try{
            iDepartamentoService.registrarDepartamento(departamentoRequest);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @PutMapping("/actualizacion")
    @ResponseBody
    public ResultadoResponse actualizarDepartamento(@RequestBody DepartamentoRequest departamentoRequest) {
        String mensaje = "Departamento guardado";
        boolean respuesta = true;
        try{
            iDepartamentoService.registrarDepartamento(departamentoRequest);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @DeleteMapping("/eliminacion/{id}")
    @ResponseBody
    public ResultadoResponse eliminarDepartamento(@PathVariable("id") Integer departamentoid){
        String mensaje = "Departamento eliminado";
        boolean respuesta = true;
        try{
            iDepartamentoService.eliminarDepartamento(departamentoid);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @GetMapping("/obtener/{id}")
    @ResponseBody
    public Departamento obtenerPorId(@PathVariable("id") Integer departamentoid) {
        return iDepartamentoService.obtenerPorId(departamentoid);
    }
}
