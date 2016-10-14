package br.com.claudiobasckeira.tmdbviewer.api.entities;

public class MovieGenreApiResponse {
    int id;
    String name;

    public MovieGenreApiResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
