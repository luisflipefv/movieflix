package dev.luisf.movieflix.Controller;

import dev.luisf.movieflix.Controller.request.StreamingRequest;
import dev.luisf.movieflix.Controller.response.StreamingResponse;
import dev.luisf.movieflix.entity.Streaming;
import dev.luisf.movieflix.mapper.StreamingMapper;
import dev.luisf.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings(){
        return ResponseEntity.ok(streamingService.findAll()
                .stream()
                .map(StreamingMapper::toResponseStreaming)
                .toList());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@Valid @RequestBody StreamingRequest request){
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming saved = streamingService.save(newStreaming);
        StreamingResponse response = StreamingMapper.toResponseStreaming(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id){
        return streamingService.getById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toResponseStreaming(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByStreamingId(@PathVariable Long id){
        streamingService.deleteStreaming(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
