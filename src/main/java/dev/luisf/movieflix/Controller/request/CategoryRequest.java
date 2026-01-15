package dev.luisf.movieflix.Controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@Schema(type = "string", description = "Nome da categoria")
                              @NotEmpty(message = "Nome da categoria obrigatorio")
                              String name) {
}
