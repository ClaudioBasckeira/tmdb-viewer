package br.com.claudiobasckeira.tmdbviewer.api.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieGenreApiResponse;
import br.com.claudiobasckeira.tmdbviewer.api.entities.MovieGenresApiResponse;
import br.com.claudiobasckeira.tmdbviewer.database.entities.MovieGenreRecord;

public class MovieGenreRecordListMapper {
    public static List<MovieGenreRecord> toMovieGenreRecordList(MovieGenresApiResponse apiResponse) throws IllegalArgumentException {
        if (apiResponse == null) throw new IllegalArgumentException();
        List<MovieGenreRecord> mappedList = new ArrayList<>();
        List<MovieGenreApiResponse> apiResponseList = apiResponse.getGenres();

        for (MovieGenreApiResponse element : apiResponseList) {
            mappedList.add(MovieGenreRecordMapper.toMovieGenreRecord(element));
        }

        return mappedList;
    }
}
