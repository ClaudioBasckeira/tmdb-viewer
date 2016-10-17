package br.com.claudiobasckeira.tmdbviewer.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    private static final String API_KEY_PARAM = "api_key";
    private String apiKey;
    private String apiHost;

    public ApiInterceptor(String apiKey, String apiHost) {
        this.apiKey = apiKey;
        this.apiHost = apiHost;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!apiHost.equals(request.url().host())) {
            return chain.proceed(request);
        }

        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.setEncodedQueryParameter(API_KEY_PARAM, apiKey);

        Request.Builder builder = request.newBuilder();
        builder.url(urlBuilder.build());

        return chain.proceed(builder.build());
    }
}
