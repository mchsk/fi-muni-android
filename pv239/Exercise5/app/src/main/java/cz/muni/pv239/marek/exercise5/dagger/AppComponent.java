package cz.muni.pv239.marek.exercise5.dagger;

import javax.inject.Singleton;

import cz.muni.pv239.marek.exercise5.RecyclerViewActivity;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(RecyclerViewActivity activity);
}
