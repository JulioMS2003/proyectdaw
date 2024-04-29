package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Rol;
import pe.edu.cibertec.proyectdaw.model.bd.Usuario;
import pe.edu.cibertec.proyectdaw.model.dto.security.UsuarioSecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DetalleUsuarioService implements UserDetailsService {
    private IUsuarioService iUsuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = iUsuarioService.obtenerPorId(username);
        return autenticacionUsuario(usuario, obtenerListaRolesUsuario(usuario.getRoles()));
    }
    private List<GrantedAuthority> obtenerListaRolesUsuario(Set<Rol> listRoles){
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for(Rol rol: listRoles){
            grantedAuthorityList.add(new SimpleGrantedAuthority(rol.getNomrol()));
        }
        return grantedAuthorityList;
    }
    private UserDetails autenticacionUsuario(Usuario usuario, List<GrantedAuthority> authorityList){
        UsuarioSecurity usuarioSecurity = new UsuarioSecurity(
                usuario.getUsuarioid(),
                usuario.getClave(), usuario.getEstado(),
                true, true, true, authorityList
        );
        usuarioSecurity.setNombres(usuario.getNomusuario());
        usuarioSecurity.setApellidos(usuario.getApeusuario());
        usuarioSecurity.setUsuarioid(usuario.getUsuarioid());
        return usuarioSecurity;
    }
}
