package me.vannen.mvvmsample;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import me.vannen.mvvmsample.di.AppComponent;
import me.vannen.mvvmsample.di.DaggerAppComponent;
import timber.log.Timber;

public class MvvmApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
