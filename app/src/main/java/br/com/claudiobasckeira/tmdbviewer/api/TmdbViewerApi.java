package br.com.claudiobasckeira.tmdbviewer.api;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import br.com.claudiobasckeira.tmdbviewer.TmdbViewerApplication;
import br.com.claudiobasckeira.tmdbviewer.api.entities.ConfigurationApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieGenresApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.entities.MoviesApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.mappers.MovieGenreRecordListMapper;
import br.com.claudiobasckeira.tmdbviewer.api.mappers.MovieListMapper;
import br.com.claudiobasckeira.tmdbviewer.database.entities.MovieGenreRecord;
import br.com.claudiobasckeira.tmdbviewer.database.helper.TmdbViewerDatabaseHelper;
import br.com.claudiobasckeira.tmdbviewer.events.GetConfigurationAndGenresEvent;
import br.com.claudiobasckeira.tmdbviewer.events.GetUpcomingMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.genre.GenreManager;
import br.com.claudiobasckeira.tmdbviewer.preferences.TmdbViewerPreferences;
import br.com.claudiobasckeira.tmdbviewer.preferences.TmdbViewerPreferences_;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;
import de.greenrobot.event.EventBus;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean
public class TmdbViewerApi {
    public static final String API_KEY = "1f54bd990f1cdfb230adb312546d765d";
    public static final String PROTOCOL = "https";
    public static final String ENDPOINT = "api.themoviedb.org";
    public static final String API_VERSION = "3";

    @RootContext
    Context context;

    @App
    TmdbViewerApplication app;

    @Pref
    TmdbViewerPreferences_ prefs;

    @Bean
    GenreManager genreManager;

    private Services services;

    @AfterInject
    protected void init() {
        EventBus.getDefault().register(this);

        ApiInterceptor interceptor = new ApiInterceptor(API_KEY, ENDPOINT);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PROTOCOL+"://"+ENDPOINT+"/"+API_VERSION+"/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        services = retrofit.create(Services.class);
    }

    public void onEventBackgroundThread(GetUpcomingMoviesEvent.Request request) {
        GetUpcomingMoviesEvent.Response response;

        try {
            Response<MoviesApiResponse> apiResponse = services.getUpcomingMovies().execute();

            if(apiResponse.isSuccessful()) {
                List<Movie> movieList = MovieListMapper.toMovieList(apiResponse.body());
                response = new GetUpcomingMoviesEvent.Response(movieList);
            } else {
                Throwable throwable = new Throwable(apiResponse.message());
                response = new GetUpcomingMoviesEvent.Response(throwable);
            }
            EventBus.getDefault().post(response);

        } catch (IOException e) {
            e.printStackTrace();
            response = new GetUpcomingMoviesEvent.Response(e);
            EventBus.getDefault().post(response);
        }
    }

    public void onEventBackgroundThread(GetConfigurationAndGenresEvent.Request request) {
        GetConfigurationAndGenresEvent.Response response;

        try {
            getConfiguration();
            getMovieGenres();

            response = new GetConfigurationAndGenresEvent.Response();
            EventBus.getDefault().post(response);
        } catch (IOException e) {
            e.printStackTrace();
            response = new GetConfigurationAndGenresEvent.Response(e);
            EventBus.getDefault().post(response);
        }
    }

    protected void getConfiguration() throws IOException{
        try {
            Response<ConfigurationApiResponse> apiResponse = services.getConfiguration().execute();
            if(apiResponse.isSuccessful()) {
                prefs.imagesBaseUrl().put(apiResponse.body().getImages().getBaseUrl());
            } else {
                if(!prefs.imagesBaseUrl().exists()) throw new IOException(apiResponse.message());
            }
        } catch (IOException e) {
            //We can cut some slack if there is already an older version of the info locally
            if(!prefs.imagesBaseUrl().exists()) throw e;
        }
    }

    protected void getMovieGenres() throws IOException{
        try {
            Response<MovieGenresApiResponse> apiResponse = services.getMovieGenres().execute();

            if(apiResponse.isSuccessful()) {
                final List<MovieGenreRecord> movieGenreRecordList = MovieGenreRecordListMapper.toMovieGenreRecordList(apiResponse.body());
                genreManager.setGenres(movieGenreRecordList);
            } else {
                if(genreManager.isDbEmpty()) throw new IOException(apiResponse.message());
            }
        } catch (IOException e) {
            //We can cut some slack if there is already an older version of the info locally
            if(genreManager.isDbEmpty()) throw e;
        }
    }
}
