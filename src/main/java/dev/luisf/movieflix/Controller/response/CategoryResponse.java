package dev.luisf.movieflix.Controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CategoryResponse(@Schema(type = "integer", description = "Id da categoria")
                               Long id,

                               @Schema(type = "string", description = "Nome da categoria")
                               String name) {
}
