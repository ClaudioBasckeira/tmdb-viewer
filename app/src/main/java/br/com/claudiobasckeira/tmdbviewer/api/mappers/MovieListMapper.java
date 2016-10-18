package br.com.claudiobasckeira.tmdbviewer.api.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.entities.MoviesApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.mappers.comparators.MovieApiResponseDateComparator;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

public class MovieListMapper {

    public static List<Movie> toDateOrderedMovieList(MoviesApiResponse apiResponse) throws IllegalArgumentException {
        if (apiResponse == null) throw new IllegalArgumentException();
        List<MovieApiResponse> movieApiResponseList = apiResponse.getResults();
        Collections.sort(movieApiResponseList, new MovieApiResponseDateComparator());
        return toMovieList(movieApiResponseList);
    }

    public static List<Movie> toDateOrderedMovieList(List<MovieApiResponse> movieApiResponseList) throws IllegalArgumentException {
        if (movieApiResponseList == null) throw new IllegalArgumentException();
        Collections.sort(movieApiResponseList, new MovieApiResponseDateComparator());
        return toMovieList(movieApiResponseList);
    }

    public static List<Movie> toMovieList(MoviesApiResponse apiResponse) throws IllegalArgumentException {
        if (apiResponse == null) throw new IllegalArgumentException();
        List<MovieApiResponse> movieApiResponseList = apiResponse.getResults();
        return toMovieList(movieApiResponseList);
    }

    public static List<Movie> toMovieList(List<MovieApiResponse> apiResponseList) throws IllegalArgumentException {
        if (apiResponseList == null) throw new IllegalArgumentException();
        List<Movie> mappedList = new ArrayList<>();

        for (MovieApiResponse element : apiResponseList) {
            mappedList.add(MovieMapper.toMovie(element));
        }

        return mappedList;
    }
}
