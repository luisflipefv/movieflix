package dev.luisf.movieflix.service;

import dev.luisf.movieflix.config.SecurityConfig;
import dev.luisf.movieflix.entity.User;
import dev.luisf.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;


    public User register(User user){
        String password = user.getPassword();
        user.setPassword(securityConfig.passwordEncoder().encode(password));
        return userRepository.save(user);
    }
}
