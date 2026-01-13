package dev.luisf.movieflix.Controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(@NotEmpty(message = "O titulo do filme e obrigatorio") String title,
                           String description,

                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate release,

                           double rating,
                           List<Long> categories,
                           List<Long> streamings) {
}
