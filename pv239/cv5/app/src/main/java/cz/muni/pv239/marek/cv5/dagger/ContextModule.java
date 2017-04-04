package cz.muni.pv239.marek.cv5.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    Context context() {
        return context;
    }
}
