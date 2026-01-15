package dev.luisf.movieflix.Controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StreamingResponse(@Schema(type = "integer", description = "Id do streaming")
                                Long id,

                                @Schema(type = "string", description = "Nome do streaming")
                                String name) {
}
