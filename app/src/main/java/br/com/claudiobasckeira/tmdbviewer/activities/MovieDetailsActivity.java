package br.com.claudiobasckeira.tmdbviewer.activities;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.genre.GenreManager;
import br.com.claudiobasckeira.tmdbviewer.helpers.ImageDownload;
import br.com.claudiobasckeira.tmdbviewer.helpers.TmdbViewerDateHelper;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

@EActivity(R.layout.activity_movie_details)
public class MovieDetailsActivity extends AppCompatActivity {
    @Extra
    Movie movie;

    @Bean
    ImageDownload imageDownload;

    @Bean
    GenreManager genreManager;

    @ViewById
    ImageView ivPoster;
    @ViewById
    TextView tvMovieTitle, tvMovieGenres, tvMovieReleaseDate, tvMovieOverview;

    @AfterViews
    void init() {
        Glide.with(this)
                .load(imageDownload.getImageUrl(ImageDownload.SIZE_ORIGINAL,movie.getPosterPath()))
                .placeholder(R.drawable.movie_poster_placeholder)
                .error(R.drawable.poster_not_available)
                .into(ivPoster);

        tvMovieTitle.setText(movie.getTitle());

        List<String> genreNames = new ArrayList<>();
        for(Integer genreId : movie.getGenreIds()) {
            genreNames.add(genreManager.getGenreName(genreId));
        }
        tvMovieGenres.setText(TextUtils.join(", ",genreNames));

        tvMovieReleaseDate.setText(TmdbViewerDateHelper.format(movie.getReleaseDate()));

        tvMovieOverview.setText(movie.getOverview());
    }
}
