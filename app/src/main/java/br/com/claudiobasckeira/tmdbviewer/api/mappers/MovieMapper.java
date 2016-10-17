package br.com.claudiobasckeira.tmdbviewer.api.mappers;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieApiResponse;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

public class MovieMapper {
    public static Movie toMovie(MovieApiResponse apiResponse) throws IllegalArgumentException{
        if(apiResponse == null) throw new IllegalArgumentException();
        return new Movie(
                apiResponse.getTitle(),
                apiResponse.getPosterPath(),
                apiResponse.getGenreIds(),
                apiResponse.getOverview(),
                apiResponse.getReleaseDate()
        );
    }
}