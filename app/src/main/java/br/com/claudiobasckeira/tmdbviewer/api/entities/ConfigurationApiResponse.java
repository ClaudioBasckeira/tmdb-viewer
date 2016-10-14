package br.com.claudiobasckeira.tmdbviewer.api.entities;

public class ConfigurationApiResponse {
    ConfigurationImagesApiResponse images;

    public ConfigurationApiResponse(ConfigurationImagesApiResponse images) {
        this.images = images;
    }

    public ConfigurationImagesApiResponse getImages() {
        return images;
    }

    public void setImages(ConfigurationImagesApiResponse images) {
        this.images = images;
    }
}
