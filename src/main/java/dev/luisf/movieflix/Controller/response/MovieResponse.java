package dev.luisf.movieflix.Controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(@Schema(type = "integer", description = "Id do filme")
                            Long id,

                            @Schema(type = "string", description = "Nome do filme")
                            String title,

                            @Schema(type = "string", description = "Sinopse do filme")
                            String description,

                            @Schema(type = "date", description = "Data de lançamento do filme")
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            LocalDate releaseDate,

                            @Schema(type = "double", description = "Score do filme")
                            double rating,

                            @Schema(type = "array", description = "Lista de codigos de categorias")
                            List<CategoryResponse> categories,

                            @Schema(type = "array", description = "Lista de condigos de streamings")
                            List<StreamingResponse> streamings) {
}
