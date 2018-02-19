package me.vannen.mvvmsample;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import me.vannen.mvvmsample.di.AppComponent;
import me.vannen.mvvmsample.di.DaggerAppComponent;

public class MvvmApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
