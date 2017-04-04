package cz.muni.pv239.marek.cv5;

import android.app.Application;

import cz.muni.pv239.marek.cv5.dagger.AppComponent;
import cz.muni.pv239.marek.cv5.dagger.AppModule;
import cz.muni.pv239.marek.cv5.dagger.DaggerAppComponent;

public class Cv5App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
