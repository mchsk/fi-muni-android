package cz.muni.pv239.marek.cv5.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marek on 02.03.17.
 */

public class GitHubApi {
    private final static String GITHUB_API_ENDPOINT = "https://api.github.com";
    private final GitHubService mService;

    public GitHubApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mService = retrofit.create(GitHubService.class);
    }

    public GitHubService getService() {
        return mService;
    }
}
