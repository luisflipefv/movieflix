package dev.luisf.movieflix.Controller;

import dev.luisf.movieflix.Controller.request.UserRequest;
import dev.luisf.movieflix.Controller.response.UserResponse;
import dev.luisf.movieflix.entity.User;
import dev.luisf.movieflix.mapper.UserMapper;
import dev.luisf.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register (@RequestBody UserRequest request){
        User saved = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(saved));
    };


}
