package br.com.claudiobasckeira.tmdbviewer.events.base;

import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.values.Movie;

public abstract class GetMoviesEvent {
    public abstract static class Request {
        public Request() {}
    }

    public abstract static class Response {

        private final Throwable throwable;
        private final List<Movie> body;

        public Response(Throwable throwable) {
            this.throwable = throwable;
            this.body = null;
        }

        public Response(List<Movie> body) {
            this.body = body;
            this.throwable = null;
        }

        public boolean isError() {
            return throwable != null;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public List<Movie> getBody() {
            return body;
        }
    }
}
