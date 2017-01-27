package br.com.claudiobasckeira.tmdbviewer.api.entities;

import java.util.List;

public class MoviesApiResponse {
    List<MovieApiResponse> results;

    public MoviesApiResponse(List<MovieApiResponse> results) {
        this.results = results;
    }

    public List<MovieApiResponse> getResults() {
        return results;
    }

    public void setResults(List<MovieApiResponse> results) {
        this.results = results;
    }
}
