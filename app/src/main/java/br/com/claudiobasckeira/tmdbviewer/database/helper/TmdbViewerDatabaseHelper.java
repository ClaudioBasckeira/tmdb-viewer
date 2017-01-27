package br.com.claudiobasckeira.tmdbviewer.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.claudiobasckeira.tmdbviewer.database.entities.MovieGenreRecord;

public class TmdbViewerDatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "tmdbviewer.db";
    private static final int DATABASE_VERSION = 1;

    public TmdbViewerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, MovieGenreRecord.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
