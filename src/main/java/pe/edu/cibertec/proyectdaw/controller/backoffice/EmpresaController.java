package pe.edu.cibertec.proyectdaw.controller.backoffice;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Empresa;
import pe.edu.cibertec.proyectdaw.model.dto.request.EmpresaRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IEmpresaService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    private IEmpresaService iEmpresaService;

    @GetMapping("")
    public String viewEmpresa(Model model,
                              @RequestParam(value = "nropag", defaultValue = "1", required = false) Integer nropag){
        List<Empresa> listaEmpresas = iEmpresaService.listarEmpresa();
        Integer nropaginas = listaEmpresas.size() % 20 == 0 ? listaEmpresas.size() / 20 : listaEmpresas.size() / 20 + 1;
        if(nropag < 1) nropag = 1;
        if(nropag > nropaginas) nropag = nropaginas;
        model.addAttribute("listaEmpresas",
                iEmpresaService.paginacionEmpresas(nropag));
        model.addAttribute("nropaginas", nropaginas);
        model.addAttribute("nropag", nropag);
        return  "backoffice/empresa/viewempresa";
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<Empresa> listarEmpresas(){
        return iEmpresaService.listarEmpresasOrdenadasPorNombreAsc();
    }

    @RequestMapping(value = "/guardar", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public ResultadoResponse guardarEmpresa(@RequestBody EmpresaRequest empresaRequest) {
        String mensaje = "Empresa guardada";
        boolean respuesta = true;
        try{
            iEmpresaService.guardarEmpresa(empresaRequest);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
