package br.com.claudiobasckeira.tmdbviewer;

import android.app.Application;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

import br.com.claudiobasckeira.tmdbviewer.api.TmdbViewerApi;

@EApplication
public class TmdbViewerApplication extends Application {
    @Bean
    TmdbViewerApi tmdbViewerApi;
}
