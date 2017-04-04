package cz.muni.pv239.marek.cv5.dagger;

import android.app.Application;

/**
 * Created by marek on 04.04.17.
 */

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
