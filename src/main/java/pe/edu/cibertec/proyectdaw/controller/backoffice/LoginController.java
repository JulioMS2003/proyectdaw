package pe.edu.cibertec.proyectdaw.controller.backoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.proyectdaw.model.dto.security.UsuarioSecurity;
import pe.edu.cibertec.proyectdaw.service.IUsuarioService;

import java.util.Date;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {
    private IUsuarioService iUsuarioService;

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
        session.setAttribute("username", usuario.getUsername());
        iUsuarioService.actualizarUltimoLogin(new Date(), usuario.getUsername());
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "backoffice/auth/dashboard";
    }

    @GetMapping("/cambiarPassword")
    public String cambiarPassword(){
        return "backoffice/auth/frmcambiarpassword";
    }

    @PostMapping("/cambiarPassword")
    public String cambiarPassword(@RequestParam("password1") String password1,
                                  @RequestParam("password2") String password2,
                                  HttpServletRequest request) {
        if(!password1.equals(password2)) {
            return "redirect:/auth/cambiarPassword?error";
        } else {
            HttpSession session = request.getSession();
            iUsuarioService.actualizarPassword(password1, session.getAttribute("username").toString());
            return "redirect:/auth/cambiarPassword?success";
        }
    }
}
