package dev.luisf.movieflix.service;

import dev.luisf.movieflix.entity.Category;
import dev.luisf.movieflix.entity.Movie;
import dev.luisf.movieflix.entity.Streaming;
import dev.luisf.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie){
        movie.setCategories(this.findCatogories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    private List<Category> findCatogories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.getCategoryById(category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.getById(streaming.getId()).ifPresent(streamingsFound::add));
        return streamingsFound;
    }

    public Optional<Movie> getByMovieId(Long id){
        return movieRepository.findById(id);
    }

    public Optional<Movie> update(Long movieId, Movie movieToUpdate){
        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if (optMovie.isPresent()){

            List<Category> categories =  this.findCatogories(movieToUpdate.getCategories());
            List<Streaming> streamings = this.findStreamings(movieToUpdate.getStreamings());

            Movie movie = optMovie.get();
            movie.setTitle(movieToUpdate.getTitle());
            movie.setDescription(movieToUpdate.getDescription());
            movie.setReleaseDate(movieToUpdate.getReleaseDate());
            movie.setRating(movieToUpdate.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);

        }
        return Optional.empty();
    }

    public List<Movie> findByCategory (Long categoryId){
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }

}
