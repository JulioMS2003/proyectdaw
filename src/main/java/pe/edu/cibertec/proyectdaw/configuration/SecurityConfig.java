package pe.edu.cibertec.proyectdaw.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.edu.cibertec.proyectdaw.service.DetalleUsuarioService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private DetalleUsuarioService  detalleUsuarioService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {
           auth.requestMatchers("/auth/login",
                   "/resources/**", "/static/**", "/css/**",
                   "/js/**").permitAll();
           auth.anyRequest().authenticated();
        });
        http.formLogin(login -> {
            login.loginPage("/auth/login");
            login.loginProcessingUrl("/auth/login");
            login.defaultSuccessUrl("/auth/login-success", true);
            login.usernameParameter("username");
            login.passwordParameter("password");
        });
        http.logout(logout -> {
            logout.logoutSuccessUrl("/auth/login?logout")
                    .invalidateHttpSession(true);
        });
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(detalleUsuarioService);
        daoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }
}
