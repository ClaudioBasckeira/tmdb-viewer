package br.com.claudiobasckeira.tmdbviewer.helpers;

import android.support.v4.widget.SwipeRefreshLayout;

/* There is a bug in the swipe refresh layout where the loading icon won't show when called
   manually. This approach solves it. */
public class SwipeRefreshWorkaround {
    public static void setRefreshing(final SwipeRefreshLayout swipeRefreshLayout, final boolean isRefreshing) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(isRefreshing);
            }
        });
    }
}
