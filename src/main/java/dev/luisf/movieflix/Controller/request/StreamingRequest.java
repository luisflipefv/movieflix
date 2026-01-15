package dev.luisf.movieflix.Controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@Schema(type = "string", description = "Nome do filme")
                               @NotEmpty(message = "Nome do streaming obrigatorio")
                               String name) {
}
