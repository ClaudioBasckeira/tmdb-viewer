package br.com.claudiobasckeira.tmdbviewer.events;

import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.events.base.GetMoviesEvent;
import br.com.claudiobasckeira.tmdbviewer.values.Movie;

public class SearchMoviesEvent extends GetMoviesEvent {
    public static class Request extends GetMoviesEvent.Request {
        private final String query;

        public Request(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
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
