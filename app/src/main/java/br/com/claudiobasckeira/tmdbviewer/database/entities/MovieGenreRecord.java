package br.com.claudiobasckeira.tmdbviewer.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "genres")
public class MovieGenreRecord {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String name;

    public MovieGenreRecord() {
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
