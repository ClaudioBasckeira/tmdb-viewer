package br.com.claudiobasckeira.tmdbviewer.viewgroups;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.activities.MovieDetailsActivity_;
import br.com.claudiobasckeira.tmdbviewer.genre.GenreManager;
import br.com.claudiobasckeira.tmdbviewer.helpers.TmdbViewerDateHelper;
import br.com.claudiobasckeira.tmdbviewer.helpers.ImageDownload;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

@EViewGroup(R.layout.viewgroup_movie_list_item)
public class MovieListItem extends CardView implements View.OnClickListener {
    @Bean
    ImageDownload imageDownload;

    @Bean
    GenreManager genreManager;

    @ViewById
    ImageView ivPoster;
    @ViewById
    TextView tvMovieTitle, tvMovieGenres, tvMovieReleaseDate;

    private Movie movie;

    public MovieListItem(Context context) {
        super(context);
        setUseCompatPadding(true);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }

    @AfterViews
    void init() {

    }

    public void bind(Movie movie) {
        setOnClickListener(this);

        this.movie = movie;

        Glide.with(getContext())
                .load(imageDownload.getImageUrl(ImageDownload.SIZE_LIST,movie.getPosterPath()))
                .placeholder(R.drawable.movie_poster_placeholder)
                .error(R.drawable.poster_not_available)
                .into(ivPoster);
        tvMovieTitle.setText(movie.getTitle());

        List<String> genreNames = new ArrayList<>();
        for(Integer genreId : movie.getGenreIds()) {
            genreNames.add(genreManager.getGenreName(genreId));
        }
        tvMovieGenres.setText(TextUtils.join(", ",genreNames));

        tvMovieReleaseDate.setText(TmdbViewerDateHelper.format(movie.getReleaseDate(),getContext().getString(R.string.release_date_unavailable)));
    }

    @Override
    public void onClick(View view) {
        MovieDetailsActivity_.intent(getContext()).movie(movie).start();
    }
}
