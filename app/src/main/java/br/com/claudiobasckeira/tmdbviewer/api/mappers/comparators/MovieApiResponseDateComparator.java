package br.com.claudiobasckeira.tmdbviewer.api.mappers.comparators;

import java.util.Comparator;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieApiResponse;

public class MovieApiResponseDateComparator implements Comparator<MovieApiResponse> {
    @Override
    public int compare(MovieApiResponse one, MovieApiResponse other) {
        return one.getReleaseDate().compareTo(other.getReleaseDate());
    }
}
