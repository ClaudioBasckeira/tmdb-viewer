package br.com.claudiobasckeira.tmdbviewer.activities;

import org.androidannotations.annotations.EActivity;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.activities.base.MovieListActivity;
import br.com.claudiobasckeira.tmdbviewer.events.GetUpcomingMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.events.base.GetMoviesEvent;

@EActivity(R.layout.activity_movie_list)
public class UpcomingMoviesActivity extends MovieListActivity {
    @Override
    protected GetMoviesEvent.Request instantiateGetMoviesEvent() {
        return new GetUpcomingMoviesEvent.Request();
    }

    public void onEventMainThread(GetUpcomingMoviesEvent.Response response) {
        handleEventBusResponse(response);
    }
}
