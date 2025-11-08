package dev.luisf.movieflix.repository;

import dev.luisf.movieflix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
