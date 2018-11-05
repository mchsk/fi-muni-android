package cz.muni.pv239.marek.exercise2.api;

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
                .build();

        mService = retrofit.create(GitHubService.class);
    }

    public GitHubService getService() {
        return mService;
    }
}
