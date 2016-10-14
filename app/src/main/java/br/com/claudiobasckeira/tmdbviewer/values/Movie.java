package br.com.claudiobasckeira.tmdbviewer.values;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Movie{
    String title;
    String posterPath;
    List<Integer> genreIds;
    String overview;
    String releaseDate;

    public Movie() {
    }

    public Movie(String title, String posterPath, List<Integer> genreIds, String overview, String releaseDate) {
        this.title = title;
        this.posterPath = posterPath;
        this.genreIds = genreIds;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
