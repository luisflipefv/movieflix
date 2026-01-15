package dev.luisf.movieflix.Controller;

import dev.luisf.movieflix.Controller.request.StreamingRequest;
import dev.luisf.movieflix.Controller.response.StreamingResponse;
import dev.luisf.movieflix.entity.Streaming;
import dev.luisf.movieflix.mapper.StreamingMapper;
import dev.luisf.movieflix.service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
@Tag(name = "Streaming", description = "Recurso responsavel pelo gerenciamento dos streamings.")
public class StreamingController {

    private final StreamingService streamingService;

    @Operation(summary = "Buscar streamings", description = "Metodo responsavel por retornar a lista de todos os streamings cadastrados", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os streamings cadastrados", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings(){
        return ResponseEntity.ok(streamingService.findAll()
                .stream()
                .map(StreamingMapper::toResponseStreaming)
                .toList());
    }

    @Operation(summary = "Salvar streaming", description = "metodo responsavel pelo salvamento de um novo streaming", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Streaming salvo com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@Valid @RequestBody StreamingRequest request){
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming saved = streamingService.save(newStreaming);
        StreamingResponse response = StreamingMapper.toResponseStreaming(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Buscar streaming por id", description = "metodo responsavel por retornar um streaming por id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Streaming encontrado com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @ApiResponse(responseCode = "404", description = "Streaming nao encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id){
        return streamingService.getById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toResponseStreaming(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar streaming por id", description = "metodo responsavel por deletar dados de um streaming por id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Streaming deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Streaming nao encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByStreamingId(@PathVariable Long id){
        streamingService.deleteStreaming(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
