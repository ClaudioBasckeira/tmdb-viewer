package br.com.claudiobasckeira.tmdbviewer.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.activities.base.EventAwareActivity;
import br.com.claudiobasckeira.tmdbviewer.adapters.MovieListAdapter;
import br.com.claudiobasckeira.tmdbviewer.events.GetUpcomingMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.util.SwipeRefreshWorkaround;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;
import de.greenrobot.event.EventBus;

@EActivity(R.layout.activity_upcoming_movies)
public class UpcomingMoviesActivity extends EventAwareActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener{
    @Bean
    MovieListAdapter movieListAdapter;

    @ViewById
    protected SwipeRefreshLayout srlUpcomingMovies;

    @ViewById
    protected ListView lvUpcomingMovies;

    @AfterViews
    protected void initMovieList() {
        srlUpcomingMovies.setOnRefreshListener(this);
        SwipeRefreshWorkaround.setRefreshing(srlUpcomingMovies, true);

        lvUpcomingMovies.setAdapter(movieListAdapter);
        lvUpcomingMovies.setOnItemClickListener(this);

        EventBus.getDefault().post(new GetUpcomingMoviesEvent.Request());
    }

    @Override
    public void onRefresh() {
        EventBus.getDefault().post(new GetUpcomingMoviesEvent.Request());
    }

    public void onEventMainThread(GetUpcomingMoviesEvent.Response response) {
        SwipeRefreshWorkaround.setRefreshing(srlUpcomingMovies, false);
        if (!response.isError()) {
            movieListAdapter.updateData(response.getBody());
        } else {
            //TODO: Treat specific errors? Maybe a class with all error definitions
            Toast.makeText(this, "Error updating list", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MovieDetailsActivity_.intent(this).movie((Movie)movieListAdapter.getItem(i)).start();
    }
}
