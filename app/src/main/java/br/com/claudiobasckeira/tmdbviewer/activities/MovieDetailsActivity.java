package br.com.claudiobasckeira.tmdbviewer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.genre.GenreManager;
import br.com.claudiobasckeira.tmdbviewer.preferences.TmdbViewerPreferences_;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

import static java.security.AccessController.getContext;

@EActivity(R.layout.activity_movie_details)
public class MovieDetailsActivity extends AppCompatActivity {
    @Extra
    Movie movie;

    @Pref
    TmdbViewerPreferences_ prefs;

    @Bean
    GenreManager genreManager;

    @ViewById
    ImageView ivPoster;
    @ViewById
    TextView tvMovieName, tvMovieGenre, tvMovieReleaseDate, tvMovieOverview;

    @AfterViews
    void init() {
        //TODO: improve this and check if exists, also extract path generation to a helper class and setup a cache!
        Glide.with(this)
                .load(prefs.imagesBaseUrl().get()+"/original/"+movie.getPosterPath())
                .into(ivPoster);

        tvMovieName.setText(movie.getTitle());

        List<String> genreNames = new ArrayList<>();
        for(Integer genreId : movie.getGenreIds()) {
            genreNames.add(genreManager.getGenreName(genreId));
        }
        tvMovieGenre.setText(TextUtils.join(", ",genreNames));

        //TODO: Improve this
        tvMovieReleaseDate.setText(DateTime.parse(movie.getReleaseDate()).toString(DateTimeFormat.forPattern("MM/dd/yyyy")));

        tvMovieOverview.setText(movie.getOverview());
    }
}
