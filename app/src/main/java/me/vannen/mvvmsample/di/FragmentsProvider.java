package me.vannen.mvvmsample.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.vannen.mvvmsample.ui.calculator.CalculatorFragment;

@Module
public abstract class FragmentsProvider {

    @ContributesAndroidInjector
    abstract CalculatorFragment bindsCalculatorFragment();
}
