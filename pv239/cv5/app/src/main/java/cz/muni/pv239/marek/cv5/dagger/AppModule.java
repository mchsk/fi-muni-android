package cz.muni.pv239.marek.cv5.dagger;

import android.app.Application;

import javax.inject.Singleton;

import cz.muni.pv239.marek.cv5.recyclerview.WatchersAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by marek on 04.04.17.
 */

@Module
public class AppModule {
    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application application() {
        return app;
    }

//    @Provides @Singleton
//    public WatchersAdapter provideWatchersAdapter() {
//        return new WatchersAdapter();
//    }
}