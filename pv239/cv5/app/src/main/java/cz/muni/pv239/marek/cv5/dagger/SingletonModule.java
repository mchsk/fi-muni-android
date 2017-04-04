package cz.muni.pv239.marek.cv5.dagger;

import javax.inject.Singleton;
import cz.muni.pv239.marek.cv5.MainActivity;
import dagger.Component;


/**
 * Created by marek on 04.04.17.
 */

@Component(modules={ContextModule.class})
@Singleton
public interface SingletonModule {
    void inject(MainActivity mainActivity);
}
