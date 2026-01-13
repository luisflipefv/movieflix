package dev.luisf.movieflix.Controller;

import dev.luisf.movieflix.Controller.response.LoginResponse;
import dev.luisf.movieflix.config.SecurityConfig;
import dev.luisf.movieflix.config.SecurityConfig.*;

import dev.luisf.movieflix.Controller.request.LoginRequest;
import dev.luisf.movieflix.Controller.request.UserRequest;
import dev.luisf.movieflix.Controller.response.UserResponse;
import dev.luisf.movieflix.config.TokenService;
import dev.luisf.movieflix.entity.User;
import dev.luisf.movieflix.exception.UsernameOrPasswordInvalidException;
import dev.luisf.movieflix.mapper.UserMapper;
import dev.luisf.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register (@RequestBody UserRequest request){
        User saved = userService.register(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(saved));
    };

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request){
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e){
            throw new UsernameOrPasswordInvalidException("Nome de usuario ou senha inválido");
        }
    }
}
