package br.com.claudiobasckeira.tmdbviewer.api.entities;


public class ConfigurationImagesApiResponse {
    String baseUrl;

    public ConfigurationImagesApiResponse(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
