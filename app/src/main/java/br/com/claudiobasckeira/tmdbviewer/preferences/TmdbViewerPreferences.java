package br.com.claudiobasckeira.tmdbviewer.preferences;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface TmdbViewerPreferences {
    String imagesBaseUrl();
}
