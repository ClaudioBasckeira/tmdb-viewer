package br.com.claudiobasckeira.tmdbviewer.api.mappers;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieGenreApiResponse;
import br.com.claudiobasckeira.tmdbviewer.database.entities.MovieGenreRecord;

public class MovieGenreRecordMapper {

    public static MovieGenreRecord toMovieGenreRecord(MovieGenreApiResponse apiResponse) {
        MovieGenreRecord mapped = new MovieGenreRecord();
        mapped.setId(apiResponse.getId());
        mapped.setName(apiResponse.getName());

        return mapped;
    }
}
