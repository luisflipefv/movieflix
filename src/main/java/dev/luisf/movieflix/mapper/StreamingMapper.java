package dev.luisf.movieflix.mapper;

import dev.luisf.movieflix.Controller.request.StreamingRequest;
import dev.luisf.movieflix.Controller.response.StreamingResponse;
import dev.luisf.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest request){
        return Streaming.builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toResponseStreaming(Streaming response){
        return StreamingResponse.builder()
                .id(response.getId())
                .name(response.getName())
                .build();
    }
}
