package br.com.claudiobasckeira.tmdbviewer.api.mappers;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieApiResponse;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

public class MovieMapper {
    public static Movie toMovie(MovieApiResponse apiResponse) {
        String releaseDate = apiResponse.getReleaseDate() == null ? "" : apiResponse.getReleaseDate().toString();

        Movie mapped = new Movie(
                apiResponse.getTitle(),
                apiResponse.getPosterPath(),
                apiResponse.getGenreIds(),
                apiResponse.getOverview(),
                releaseDate
        );

        return mapped;
    }
}