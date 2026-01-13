package dev.luisf.movieflix.Controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotEmpty(message = "Nome da categoria obrigatorio") String name) {
}
