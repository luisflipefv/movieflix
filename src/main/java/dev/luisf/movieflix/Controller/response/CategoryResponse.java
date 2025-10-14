package dev.luisf.movieflix.Controller.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
