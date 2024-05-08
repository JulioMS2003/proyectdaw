package pe.edu.cibertec.proyectdaw.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.model.dto.request.UsuarioRequest;
import pe.edu.cibertec.proyectdaw.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.proyectdaw.service.IUsuarioService;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private IUsuarioService iUsuarioService;
    @GetMapping("")
    public String viewUsuario(Model model,
                              @RequestParam(value = "nropag", defaultValue = "1", required = false) Integer nropag) {
        List<Usuario> listaUsuarios = iUsuarioService.listarUsuario();
        System.out.println(listaUsuarios.size());
        Integer nropaginas = (listaUsuarios.size() - 1) % 20 == 0 ? (listaUsuarios.size() - 1) / 20 : (listaUsuarios.size() - 1) / 20 + 1;
        System.out.println(nropaginas);
        if(nropag < 1) nropag = 1;
        if(nropag > nropaginas) nropag = nropaginas;
        model.addAttribute("listaUsuarios",
                iUsuarioService.paginacionUsuarios(nropag));
        model.addAttribute("nropaginas", nropaginas);
        model.addAttribute("nropag", nropag);
        return "backoffice/usuario/viewusuario";
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<Usuario> listarUsuario(){
        return iUsuarioService.listarUsuariosOrdenadosPorApellidos();
    }

    @PostMapping("/guardar/nuevo")
    @ResponseBody
    public ResultadoResponse guardarNuevoUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        String mensaje = "Nuevo Usuario guardado";
        boolean respuesta = true;
        try{
            iUsuarioService.registrarNuevoUsuario(usuarioRequest);
        } catch(Exception ex) {
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @PutMapping("/actualizar")
    @ResponseBody
    public ResultadoResponse actualizarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        String mensaje = "Usuario actualizado";
        boolean respuesta = true;
        try{
            iUsuarioService.actualizarDatosUsuario1(usuarioRequest);
        } catch(Exception ex){
            mensaje = ex.getMessage();
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @GetMapping("/{usuarioid}")
    @ResponseBody
    public Usuario obtenerPorId(@PathVariable("usuarioid") Integer usuarioid) {
        return iUsuarioService.obtenerPorId(usuarioid);
    }
}
