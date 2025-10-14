package dev.luisf.movieflix.mapper;

import dev.luisf.movieflix.Controller.request.MovieRequest;
import dev.luisf.movieflix.Controller.response.CategoryResponse;
import dev.luisf.movieflix.Controller.response.MovieResponse;
import dev.luisf.movieflix.Controller.response.StreamingResponse;
import dev.luisf.movieflix.entity.Category;
import dev.luisf.movieflix.entity.Movie;
import dev.luisf.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest request){

        List<Category> categories = request.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = request.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.release())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie){

        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings().stream()
                .map(streaming -> StreamingMapper.toResponseStreaming(streaming))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .tile(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

}
