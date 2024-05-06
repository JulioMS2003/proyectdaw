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
    public String viewEmpresa(Model model){
        model.addAttribute("listaEmpresas",
                iEmpresaService.listarEmpresasOrdenadasPorNombreAsc());
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
