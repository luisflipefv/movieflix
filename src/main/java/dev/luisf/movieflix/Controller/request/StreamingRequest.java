package dev.luisf.movieflix.Controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@NotEmpty(message = "Nome do streaming obrigatorio") String name) {
}
