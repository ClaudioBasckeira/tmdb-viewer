package br.com.claudiobasckeira.tmdbviewer.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.values.Movie;
import br.com.claudiobasckeira.tmdbviewer.viewgroups.MovieListItem;
import br.com.claudiobasckeira.tmdbviewer.viewgroups.MovieListItem_;

@EBean
public class MovieListAdapter extends BaseAdapter{
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

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = MovieListItem_.build(context);
        }

        ((MovieListItem)view).bind(movieList.get(i));

        return view;
    }
}
