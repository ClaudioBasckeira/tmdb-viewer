package br.com.claudiobasckeira.tmdbviewer.values;

import org.joda.time.LocalDate;
import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Movie{
    String title;
    String posterPath;
    List<Integer> genreIds;
    String overview;
    LocalDate releaseDate;

    public Movie() {
    }

    public Movie(String title, String posterPath, List<Integer> genreIds, String overview, LocalDate releaseDate) {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
