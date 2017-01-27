package br.com.claudiobasckeira.tmdbviewer.helpers;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class TmdbViewerDateHelper {
    public static String format(LocalDate date, String failureMessage) {
        try {
            return DateTimeFormat.mediumDate().print(date);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return failureMessage;
        }
    }
}
