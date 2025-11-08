package dev.luisf.movieflix.service;

import dev.luisf.movieflix.entity.User;
import dev.luisf.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;


    public User save(User user){
        return userRepository.save(user);
    }
}
