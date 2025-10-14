package dev.luisf.movieflix.Controller;

import dev.luisf.movieflix.Controller.request.MovieRequest;
import dev.luisf.movieflix.Controller.response.MovieResponse;
import dev.luisf.movieflix.entity.Category;
import dev.luisf.movieflix.entity.Movie;
import dev.luisf.movieflix.entity.Streaming;
import dev.luisf.movieflix.mapper.MovieMapper;
import dev.luisf.movieflix.service.CategoryService;
import dev.luisf.movieflix.service.MovieService;
import dev.luisf.movieflix.service.StreamingService;
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
public class MovieController {

    private final MovieService movieService;


    @PostMapping
    public ResponseEntity<MovieResponse> save (@RequestBody MovieRequest request){
        Movie movieToSave = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(movieToSave));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAllMovies (){
        return ResponseEntity.ok(movieService.findAll().stream()
                .map(MovieMapper::toMovieResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getByMovieId(@PathVariable Long id){
        return movieService.getByMovieId(id).map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @RequestBody MovieRequest request){
        return movieService.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findMovieByCategory(@RequestParam Long categoryId){
        List<MovieResponse> movies = movieService.findByCategory(categoryId)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movies);
    }

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
