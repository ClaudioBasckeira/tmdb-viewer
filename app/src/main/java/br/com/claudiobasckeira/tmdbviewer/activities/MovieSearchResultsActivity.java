package br.com.claudiobasckeira.tmdbviewer.activities;

import android.content.Intent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.activities.base.MovieListActivity;
import br.com.claudiobasckeira.tmdbviewer.events.GetUpcomingMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.events.SearchMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.events.base.GetMoviesEvent;

import static android.app.SearchManager.QUERY;

@EActivity(R.layout.activity_movie_list)
public class MovieSearchResultsActivity extends MovieListActivity {
    @Extra
    String query;

    @AfterViews
    protected void setTitle() {
        setTitle(getString(R.string.search_results_for, query));
    }

    @Override
    protected GetMoviesEvent.Request instantiateGetMoviesEvent() {
        return new SearchMoviesEvent.Request(query);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        setTitle();
        refreshPressed();
    }

    public void onEventMainThread(SearchMoviesEvent.Response response) {
        handleEventBusResponse(response);
    }
}
