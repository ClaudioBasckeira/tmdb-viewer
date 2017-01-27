package br.com.claudiobasckeira.tmdbviewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.values.Movie;
import br.com.claudiobasckeira.tmdbviewer.viewgroups.MovieListItem;
import br.com.claudiobasckeira.tmdbviewer.viewgroups.MovieListItem_;

@EBean
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    @RootContext
    Context context;

    private List<Movie> movieList;

    @AfterInject
    void init() {
        movieList = new ArrayList<>();
    }

    public void updateData(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieListItem view = MovieListItem_.build(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.movieListItem.bind(movieList.get(position));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public MovieListItem movieListItem;

        public ViewHolder(MovieListItem v) {
            super(v);
            movieListItem = v;
        }
    }
}
