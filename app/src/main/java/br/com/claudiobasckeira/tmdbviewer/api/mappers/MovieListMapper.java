package br.com.claudiobasckeira.tmdbviewer.api.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.entities.MoviesApiResponse;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

public class MovieListMapper {
    public static List<Movie> toMovieList(MoviesApiResponse apiResponse) {
        List<Movie> mappedList = new ArrayList<>();
        List<MovieApiResponse> apiResponseList = apiResponse.getResults();

        for(MovieApiResponse element : apiResponseList) {
            mappedList.add(MovieMapper.toMovie(element));
        }

        return mappedList;
    }
}
