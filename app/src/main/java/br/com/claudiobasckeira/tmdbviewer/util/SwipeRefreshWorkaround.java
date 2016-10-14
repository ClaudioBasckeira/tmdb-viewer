package br.com.claudiobasckeira.tmdbviewer.util;

import android.support.v4.widget.SwipeRefreshLayout;

public class SwipeRefreshWorkaround {
    public static void setRefreshing(final SwipeRefreshLayout swipeRefreshLayout, final boolean isRefreshing)
    {
        swipeRefreshLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                swipeRefreshLayout.setRefreshing(isRefreshing);
            }
        });
    }
}
