package br.com.claudiobasckeira.tmdbviewer.activities.base;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InjectMenu;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.adapters.MovieListAdapter;
import br.com.claudiobasckeira.tmdbviewer.events.GetUpcomingMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.events.base.GetMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.helpers.SwipeRefreshWorkaround;
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
        //TODO: test and deal with empty lists
        //TODO: handle rotation better
        srlMovies.setOnRefreshListener(this);
        SwipeRefreshWorkaround.setRefreshing(srlMovies, true);

        rvMovies.setAdapter(movieListAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        EventBus.getDefault().post(instantiateGetMoviesEvent());
    }

    @Override
    public void onRefresh() {
        EventBus.getDefault().post(instantiateGetMoviesEvent());
    }

    public void handleEventBusResponse(GetMoviesEvent.Response response) {
        SwipeRefreshWorkaround.setRefreshing(srlMovies, false);
        if (!response.isError()) {
            movieListAdapter.updateData(response.getBody());
        } else {
            Toast.makeText(this, R.string.movie_list_error, Toast.LENGTH_LONG).show();
        }
    }

    @OptionsItem(R.id.menu_refresh)
    protected void refreshPressed() {
        SwipeRefreshWorkaround.setRefreshing(srlMovies, true);
        onRefresh();
    }

    protected abstract GetMoviesEvent.Request instantiateGetMoviesEvent();
}
