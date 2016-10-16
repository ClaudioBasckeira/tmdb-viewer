package br.com.claudiobasckeira.tmdbviewer.api.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.entities.MoviesApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.mappers.comparators.MovieApiResponseDateComparator;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

public class MovieListMapper {
    public static List<Movie> toMovieList(MoviesApiResponse apiResponse) {
        List<MovieApiResponse> movieApiResponseList = apiResponse.getResults();
        return toMovieList(movieApiResponseList);
    }

    public static List<Movie> toDateOrderedMovieList(MoviesApiResponse apiResponse) {
        List<MovieApiResponse> movieApiResponseList = apiResponse.getResults();
        Collections.sort(movieApiResponseList, new MovieApiResponseDateComparator());
        return toMovieList(movieApiResponseList);
    }

    private static List<Movie> toMovieList(List<MovieApiResponse> apiResponseList) {
        List<Movie> mappedList = new ArrayList<>();

        for(MovieApiResponse element : apiResponseList) {
            mappedList.add(MovieMapper.toMovie(element));
        }

        return mappedList;
    }
}
