package br.com.claudiobasckeira.tmdbviewer.genre;

import android.support.v4.util.ArrayMap;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import br.com.claudiobasckeira.tmdbviewer.database.entities.MovieGenreRecord;
import br.com.claudiobasckeira.tmdbviewer.database.helper.TmdbViewerDatabaseHelper;
import br.com.claudiobasckeira.tmdbviewer.values.MovieGenre;

@EBean(scope = EBean.Scope.Singleton)
public class GenreManager {
    Map<Integer, MovieGenre> genreMap;

    @OrmLiteDao(helper = TmdbViewerDatabaseHelper.class)
    RuntimeExceptionDao<MovieGenreRecord, String> movieGenreRecordDao;

    @AfterInject
    protected void init() {
        loadGenres();
    }

    public void setGenres(final List<MovieGenreRecord> movieGenreRecordList) {
        movieGenreRecordDao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (MovieGenreRecord movieGenreRecord : movieGenreRecordList) {
                    movieGenreRecordDao.createOrUpdate(movieGenreRecord);
                }
                return null;
            }
        });
        updateGenreMap(movieGenreRecordList);
    }

    private void loadGenres() {
        List<MovieGenreRecord> movieGenreRecordList = movieGenreRecordDao.queryForAll();
        updateGenreMap(movieGenreRecordList);
    }

    private void updateGenreMap(List<MovieGenreRecord> movieGenreRecordList) {
        genreMap = new ArrayMap<>();
        for (MovieGenreRecord movieGenreRecord : movieGenreRecordList) {
            MovieGenre genre = new MovieGenre(
                    movieGenreRecord.getId(),
                    movieGenreRecord.getName()
            );
            genreMap.put(movieGenreRecord.getId(), genre);
        }
    }

    public String getGenreName(int genreId) {
        if (!genreMap.containsKey(genreId)) return "";
        return genreMap.get(genreId).getName();
    }

    public boolean isDbEmpty() {
        return movieGenreRecordDao.countOf() == 0;
    }
}
