package pe.edu.cibertec.proyectdaw.controller.backoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.proyectdaw.model.dto.security.UsuarioSecurity;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "backoffice/auth/frmlogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioSecurity usuario = (UsuarioSecurity) userDetails;
        session.setAttribute("nombres", usuario.getNombres() + " " + usuario.getApellidos());
        session.setAttribute("idusuario", usuario.getUsuarioid());
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "backoffice/auth/dashboard";
    }
}
