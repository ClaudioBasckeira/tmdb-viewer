package br.com.claudiobasckeira.tmdbviewer.values;

import org.parceler.Parcel;

@Parcel
public class MovieGenre {
    int id;
    String name;

    public MovieGenre() {
    }

    public MovieGenre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
