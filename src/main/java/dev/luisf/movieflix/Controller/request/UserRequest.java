package dev.luisf.movieflix.Controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserRequest(@Schema(type = "string", description = "Nome do usuario")
                          String name,

                          @Schema(type = "string", description = "Email do usuario")
                          String email,

                          @Schema(type = "string", description = "Senha do usuario")
                          String password) {
}
