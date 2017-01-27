package br.com.claudiobasckeira.tmdbviewer.events;

import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.events.base.GetMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

public class GetUpcomingMoviesEvent extends GetMoviesEvent {
    public static class Request extends GetMoviesEvent.Request {
    }

    public static class Response extends GetMoviesEvent.Response {
        public Response(Throwable throwable) {
            super(throwable);
        }

        public Response(List<Movie> body) {
            super(body);
        }
    }
}
