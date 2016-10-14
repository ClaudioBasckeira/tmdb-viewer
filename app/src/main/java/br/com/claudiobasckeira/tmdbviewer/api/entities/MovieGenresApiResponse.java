package br.com.claudiobasckeira.tmdbviewer.api.entities;

import java.util.List;

public class MovieGenresApiResponse {
    List<MovieGenreApiResponse> genres;

    public MovieGenresApiResponse(List<MovieGenreApiResponse> genres) {
        this.genres = genres;
    }

    public List<MovieGenreApiResponse> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieGenreApiResponse> genres) {
        this.genres = genres;
    }
}
