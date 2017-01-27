package br.com.claudiobasckeira.tmdbviewer.events;

public class GetConfigurationAndGenresEvent {
    public static class Request {
        public Request() {
        }
    }

    public static class Response {

        private final Throwable throwable;

        public Response(Throwable throwable) {
            this.throwable = throwable;
        }

        public Response() {
            this.throwable = null;
        }

        public boolean isError() {
            return throwable != null;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }
}
