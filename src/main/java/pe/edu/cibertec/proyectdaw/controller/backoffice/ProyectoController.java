package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Proyecto;
import pe.edu.cibertec.proyectdaw.model.dto.request.ProyectoRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IDepartamentoService;
import pe.edu.cibertec.proyectdaw.service.IEmpresaService;
import pe.edu.cibertec.proyectdaw.service.IProyectoService;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

    private IProyectoService iProyectoService;
    private IEmpresaService iEmpresaService;
    private IDepartamentoService iDepartamentoService;
    @GetMapping("")
    public String generarProyecto(Model model,
                                  @RequestParam(value = "nropag", defaultValue = "1", required = false) Integer nropag){
        List<Proyecto> listaProyectos = iProyectoService.listarProyectos();
        Integer nropaginas = listaProyectos.size() % 20 == 0 ? listaProyectos.size() / 20 : listaProyectos.size() / 20 + 1;
        if(nropag < 1) nropag = 1;
        if(nropag > nropaginas) nropag = nropaginas;
        model.addAttribute("listaProyectos",
                iProyectoService.paginacionProyectos(nropag));
        model.addAttribute("nropaginas", nropaginas);
        model.addAttribute("nropag", nropag);
        return "backoffice/proyecto/generarproyecto";
    }

    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Proyecto buscarPorId(@PathVariable("id") Integer proyectoid) {
        return iProyectoService.buscarPorId(proyectoid);
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<Proyecto> listarProyectos(){
        return iProyectoService.listarProyectos();
    }

    @GetMapping("/nuevo")
    public String nuevoProyecto(Model model) {
        model.addAttribute("listaEmpresas",
                iEmpresaService.listarEmpresasActivasOrdenadasPorNombreAsc(true));
        model.addAttribute("listaDepartamentos",
                iDepartamentoService.listarDepartamentosOrdenadosPorNombreAsc());
        return "backoffice/proyecto/nuevoproyecto";
    }

    @PostMapping("/nuevo/generar")
    @ResponseBody
    public ResultadoResponse generarProyecto(@RequestBody ProyectoRequest proyectoRequest){
        String mensaje = "Proyecto generado.";
        boolean respuesta = true;
        try{
            iProyectoService.generarProyecto(proyectoRequest);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta).mensaje(mensaje).build();
    }

    @PutMapping("/cancelar/{id}")
    @ResponseBody
    public ResultadoResponse cancelarProyecto(@PathVariable("id") Integer proyectid) {
        String mensaje = "Proyecto cancelado";
        boolean respuesta = true;
        try{
            iProyectoService.cancelarProyecto(proyectid);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta).mensaje(mensaje).build();
    }

    @PutMapping("/finalizar/{id}")
    @ResponseBody
    public ResultadoResponse finalizarProyecto(@PathVariable("id") Integer proyectoid) {
        String mensaje = "Proyecto finalizado";
        boolean respuesta = true;
        try{
            iProyectoService.finalizarProyecto(proyectoid);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
