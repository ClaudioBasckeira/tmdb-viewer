package br.com.claudiobasckeira.tmdbviewer.api;


import br.com.claudiobasckeira.tmdbviewer.api.entities.ConfigurationApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieGenresApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.entities.MoviesApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {
    @GET("configuration")
    Call<ConfigurationApiResponse> getConfiguration();

    @GET("genre/movie/list")
    Call<MovieGenresApiResponse> getMovieGenres();

    @GET("movie/upcoming")
    Call<MoviesApiResponse> getUpcomingMovies();
}
