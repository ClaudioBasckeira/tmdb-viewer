package br.com.claudiobasckeira.tmdbviewer.helpers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class TmdbViewerDateHelper {
    public static String format(String date) {
        try {
            return DateTime.parse(date).toString(DateTimeFormat.forPattern("MM/dd/yyyy"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "";
        }
    }
}
