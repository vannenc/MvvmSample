package me.vannen.mvvmsample.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.vannen.mvvmsample.ui.MainActivity;
import me.vannen.mvvmsample.ui.MainActivityModule;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = { MainActivityModule.class, FragmentsProvider.class })
    abstract MainActivity bindsMainActivity();
}
