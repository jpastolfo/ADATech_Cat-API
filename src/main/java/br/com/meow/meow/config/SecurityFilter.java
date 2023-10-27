package br.com.meow.meow.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import br.com.meow.meow.repository.UsuarioRepository;
import br.com.meow.meow.service.TokenService;

import java.io.IOException;

//@Component
public class SecurityFilter extends OncePerRequestFilter {

  //  @Autowired
    private TokenService tokenService;
  //  @Autowired
    private UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authorization = request.getHeader("Authorization");

        if(authorization != null) {

            String token = authorization.replace("Bearer ", "");

            String login = this.tokenService.validarToken(token);
            UserDetails user = this.userRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);

    }
}