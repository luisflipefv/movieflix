package dev.luisf.movieflix.mapper;

import dev.luisf.movieflix.Controller.request.UserRequest;
import dev.luisf.movieflix.Controller.response.UserResponse;
import dev.luisf.movieflix.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
