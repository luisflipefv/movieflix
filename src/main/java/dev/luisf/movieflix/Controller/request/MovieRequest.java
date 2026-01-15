package dev.luisf.movieflix.Controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(@Schema(type = "string", description = "Nome do filme")
                           @NotEmpty(message = "O titulo do filme e obrigatorio")
                           String title,

                           @Schema(type = "string", description = "Sinopse do filme")
                           String description,

                           @Schema(type = "date", description = "Data de lançamento do filme")
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate release,

                           @Schema(type = "double", description = "Score do filme")
                           double rating,

                           @Schema(type = "array", description = "Lista de codigos de categorias")
                           List<Long> categories,

                           @Schema(type = "array", description = "Lista de condigos de streamings")
                           List<Long> streamings) {
}
