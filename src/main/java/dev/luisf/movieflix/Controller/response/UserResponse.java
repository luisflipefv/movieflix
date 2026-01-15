package dev.luisf.movieflix.Controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserResponse (@Schema(type = "integer", description = "Id do usuario")
                            Long id,

                            @Schema(type = "string", description = "Nome do usuario")
                            String name,

                            @Schema(type = "string", description = "Email do usuario")
                            String email) {
}
