package br.com.claudiobasckeira.tmdbviewer.helpers;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;
import java.util.List;

import br.com.claudiobasckeira.tmdbviewer.preferences.TmdbViewerPreferences_;

@EBean(scope = EBean.Scope.Singleton)
public class ImageDownload {
    public static final String SIZE_ORIGINAL = "original";
    public static final String SIZE_LIST = "w342";

    private List<String> allowedSizes;

    @Pref
    TmdbViewerPreferences_ prefs;

    @AfterInject
    public void init() {
        allowedSizes = new ArrayList<>();
        allowedSizes.add(SIZE_ORIGINAL);
        allowedSizes.add(SIZE_LIST);
    }

    public String getImageUrl(String size, String filename) {
        if(!allowedSizes.contains(size)) size = SIZE_LIST;

        String imageUrl = prefs.imagesBaseUrl().get()+"/"+size+"/"+filename;

        return imageUrl;
    }
}
