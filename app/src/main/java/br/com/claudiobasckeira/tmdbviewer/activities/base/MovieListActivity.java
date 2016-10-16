package br.com.claudiobasckeira.tmdbviewer.activities.base;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InjectMenu;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.adapters.MovieListAdapter;
import br.com.claudiobasckeira.tmdbviewer.events.GetUpcomingMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.events.base.GetMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.helpers.SwipeRefreshWorkaround;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;
import de.greenrobot.event.EventBus;

@EActivity(R.layout.activity_movie_list)
@OptionsMenu(R.menu.options_menu)
public abstract class MovieListActivity extends EventAwareActivity implements SwipeRefreshLayout.OnRefreshListener{
    @Bean
    protected MovieListAdapter movieListAdapter;

    @ViewById
    protected SwipeRefreshLayout srlMovies;

    @ViewById
    protected RecyclerView rvMovies;

    @ViewById
    protected TextView tvEmptyList;

    @InstanceState
    protected List<Movie> movieList;

    protected LinearLayoutManager linearLayoutManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @AfterViews
    protected void initMovieList() {
        srlMovies.setOnRefreshListener(this);

        rvMovies.setAdapter(movieListAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(linearLayoutManager);

        if(movieList == null) {
            fetchNewData();
        }
        else {
            updateAdapterData();
        }
    }

    @Override
    public void onRefresh() {
        fetchNewData();
    }

    public void fetchNewData() {
        SwipeRefreshWorkaround.setRefreshing(srlMovies, true);
        EventBus.getDefault().post(instantiateGetMoviesEvent());
    }

    public void handleEventBusResponse(GetMoviesEvent.Response response) {
        SwipeRefreshWorkaround.setRefreshing(srlMovies, false);
        if (!response.isError()) {
            movieList = response.getBody();
            updateAdapterData();
        } else {
            Toast.makeText(this, R.string.movie_list_error, Toast.LENGTH_LONG).show();
        }
    }

    protected void updateAdapterData() {
        movieListAdapter.updateData(movieList);
        tvEmptyList.setVisibility(movieList.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @OptionsItem(R.id.menu_refresh)
    protected void refreshPressed() {
        fetchNewData();
    }

    protected abstract GetMoviesEvent.Request instantiateGetMoviesEvent();
}
