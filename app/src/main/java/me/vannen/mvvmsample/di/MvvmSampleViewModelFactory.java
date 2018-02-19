package me.vannen.mvvmsample.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import me.vannen.mvvmsample.ui.calculator.CalculatorViewModel;

public class MvvmSampleViewModelFactory implements ViewModelProvider.Factory {

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(CalculatorViewModel.class)) {
            return (T) new CalculatorViewModel();
        }
        return null;
    }
}
