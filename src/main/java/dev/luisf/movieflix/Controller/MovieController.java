package dev.luisf.movieflix.Controller;

import dev.luisf.movieflix.Controller.request.MovieRequest;
import dev.luisf.movieflix.Controller.response.MovieResponse;
import dev.luisf.movieflix.entity.Movie;
import dev.luisf.movieflix.mapper.MovieMapper;
import dev.luisf.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieflix/movie")
@Tag(name = "Movie", description = "Recurso responsavel pelo gerenciamento dos filmes.")
public class MovieController {

    private final MovieService movieService;


    @Operation(summary = "Salvar filme", description = "metodo responsavel pelo salvamento de um novo filme", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @PostMapping
    public ResponseEntity<MovieResponse> save (@Valid @RequestBody MovieRequest request){
        Movie movieToSave = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(movieToSave));
    }

    @Operation(summary = "Buscar filmes", description = "Metodo responsavel por retornar a lista de todos os filmes cadastrados", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAllMovies (){
        return ResponseEntity.ok(movieService.findAll().stream()
                .map(MovieMapper::toMovieResponse).toList());
    }

    @Operation(summary = "Buscar filme por id", description = "metodo responsavel por retornar um filme por id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme nao encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getByMovieId(@PathVariable Long id){
        return movieService.getByMovieId(id).map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Alterar filme por id", description = "metodo responsavel por alterar dados de um filme por id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme alterado com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme nao encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id,@Valid @RequestBody MovieRequest request){
        return movieService.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar filmes por categoria", description = "Metodo responsavel por retornar a lista de todos os filmes cadastrados na categoria selecionada", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados em uma categoria", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findMovieByCategory(@RequestParam Long categoryId){
        List<MovieResponse> movies = movieService.findByCategory(categoryId)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "Deletar filme por id", description = "metodo responsavel por deletar dados de um filme por id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Filme nao encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        Optional<Movie> movie = movieService.getByMovieId(id);
        if (movie.isPresent()){
            movieService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
