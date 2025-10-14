package dev.luisf.movieflix.Controller.request;

import lombok.Builder;

@Builder
public record CategoryRequest(String name) {
}
