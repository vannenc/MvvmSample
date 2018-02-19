package me.vannen.mvvmsample.ui;

import dagger.Module;
import dagger.Provides;
import me.vannen.mvvmsample.di.MvvmSampleViewModelFactory;

@Module
public class MainActivityModule {

    @Provides
    MvvmSampleViewModelFactory providesMvvmSampleViewModelFactory() {
        return new MvvmSampleViewModelFactory();
    }
}
