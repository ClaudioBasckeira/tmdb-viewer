package br.com.claudiobasckeira.tmdbviewer.api.mappers;

import org.joda.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieApiResponse;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

import static org.junit.Assert.*;

public class MovieMapperTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void toMovie() throws Exception {
        LocalDate currentDate = new LocalDate();

        MovieApiResponse param = new MovieApiResponse(
                "test title",
                "test poster path",
                Arrays.asList(1,2),
                "test overview",
                currentDate
        );

        MovieApiResponse emptyParam = new MovieApiResponse(
                null,
                null,
                null,
                null,
                null
        );

        MovieApiResponse nullParam = null;

        Movie movie = new Movie(
                "test title",
                "test poster path",
                Arrays.asList(1,2),
                "test overview",
                currentDate
        );

        Movie emptyMovie = new Movie(
                null,
                null,
                null,
                null,
                null
        );

        Movie mappedMovie = MovieMapper.toMovie(param);
        assertEquals(mappedMovie.getTitle(), movie.getTitle());
        assertEquals(mappedMovie.getPosterPath(), movie.getPosterPath());
        assertEquals(mappedMovie.getGenreIds(), movie.getGenreIds());
        assertEquals(mappedMovie.getOverview(), movie.getOverview());
        assertEquals(mappedMovie.getReleaseDate(), movie.getReleaseDate());

        Movie mappedEmptyMovie = MovieMapper.toMovie(emptyParam);
        assertEquals(mappedEmptyMovie.getTitle(), emptyMovie.getTitle());
        assertEquals(mappedEmptyMovie.getPosterPath(), emptyMovie.getPosterPath());
        assertEquals(mappedEmptyMovie.getGenreIds(), emptyMovie.getGenreIds());
        assertEquals(mappedEmptyMovie.getOverview(), emptyMovie.getOverview());
        assertEquals(mappedEmptyMovie.getReleaseDate(), emptyMovie.getReleaseDate());

        exception.expect(IllegalArgumentException.class);
        MovieMapper.toMovie(nullParam);
    }

}