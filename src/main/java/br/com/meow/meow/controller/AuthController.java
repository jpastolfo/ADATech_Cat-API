package br.com.meow.meow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.meow.meow.dto.AutenticacaoDTO;
import br.com.meow.meow.dto.LoginResponseDTO;
import br.com.meow.meow.dto.RegistroDTO;
import br.com.meow.meow.model.Usuario;
import br.com.meow.meow.service.TokenService;
import br.com.meow.meow.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AutenticacaoDTO autenticacaoDTO) {

        var usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(autenticacaoDTO.login(), autenticacaoDTO.password());

        var authentication = this.authenticationManager.authenticate(usernamePasswordAuthentication);

        String token = this.tokenService.gerarToken((Usuario)authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody RegistroDTO registroDTO) {

        if (this.userService.buscarUsuarioPorLogin(registroDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String passwordEncrypted = new BCryptPasswordEncoder().encode(registroDTO.password());
        Usuario usuario = new Usuario(registroDTO.login(), passwordEncrypted, registroDTO.role());

        this.userService.registrar(usuario);

        return ResponseEntity.ok().build();

    }


}