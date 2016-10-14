package br.com.claudiobasckeira.tmdbviewer.viewgroups;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.activities.MovieDetailsActivity_;
import br.com.claudiobasckeira.tmdbviewer.genre.GenreManager;
import br.com.claudiobasckeira.tmdbviewer.preferences.TmdbViewerPreferences;
import br.com.claudiobasckeira.tmdbviewer.preferences.TmdbViewerPreferences_;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

@EViewGroup(R.layout.viewgroup_movie_list_item)
public class MovieListItem extends LinearLayout {
    @Pref
    TmdbViewerPreferences_ prefs;

    @Bean
    GenreManager genreManager;

    @ViewById
    ImageView ivPoster;
    @ViewById
    TextView tvMovieName, tvMovieGenre, tvMovieReleaseDate;

    public MovieListItem(Context context) {
        super(context);
    }

    public void bind(Movie movie) {
        //TODO: improve this and check if exists, also extract path generation to a helper class and setup a cache!
        Glide.with(getContext())
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
    }
}
