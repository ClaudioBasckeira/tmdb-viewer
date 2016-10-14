package br.com.claudiobasckeira.tmdbviewer;

import android.app.Application;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

import java.util.List;
import java.util.Map;

import br.com.claudiobasckeira.tmdbviewer.api.TmdbViewerApi;
import br.com.claudiobasckeira.tmdbviewer.values.MovieGenre;

@EApplication
public class TmdbViewerApplication extends Application {
    @Bean
    TmdbViewerApi tmdbViewerApi;


}
