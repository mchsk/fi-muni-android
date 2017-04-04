package cz.muni.pv239.marek.cv5.dagger;

import android.content.Context;

import javax.inject.Singleton;

import cz.muni.pv239.marek.cv5.api.GitHubService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private final static String GITHUB_API_ENDPOINT = "https://api.github.com";
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GitHubService.class);
    }
}
