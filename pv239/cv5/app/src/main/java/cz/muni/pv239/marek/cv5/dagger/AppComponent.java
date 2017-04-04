package cz.muni.pv239.marek.cv5.dagger;

import javax.inject.Singleton;

import cz.muni.pv239.marek.cv5.RecyclerViewActivity;
import dagger.Component;

/**
 * Created by marek on 04.04.17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App application);
    void inject(RecyclerViewActivity activity);
}