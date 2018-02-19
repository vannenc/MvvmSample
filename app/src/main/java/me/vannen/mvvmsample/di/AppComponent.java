package me.vannen.mvvmsample.di;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import me.vannen.mvvmsample.MvvmApp;

@Component(modules = { AndroidSupportInjectionModule.class, AppModule.class })
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MvvmApp app);

        AppComponent build();
    }

    @Override
    void inject(DaggerApplication instance);
}
