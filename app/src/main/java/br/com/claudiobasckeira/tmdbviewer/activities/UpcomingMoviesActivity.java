package br.com.claudiobasckeira.tmdbviewer.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.activities.base.EventAwareActivity;
import br.com.claudiobasckeira.tmdbviewer.activities.base.MovieListActivity;
import br.com.claudiobasckeira.tmdbviewer.adapters.MovieListAdapter;
import br.com.claudiobasckeira.tmdbviewer.events.GetUpcomingMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.events.base.GetMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.helpers.SwipeRefreshWorkaround;
import de.greenrobot.event.EventBus;

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
