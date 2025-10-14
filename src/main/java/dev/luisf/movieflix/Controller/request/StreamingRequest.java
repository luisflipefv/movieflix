package dev.luisf.movieflix.Controller.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
}
