package me.vannen.mvvmsample.ui.calculator;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;
import me.vannen.mvvmsample.R;
import me.vannen.mvvmsample.di.MvvmSampleViewModelFactory;

public class CalculatorFragment extends DaggerFragment {

    @Inject MvvmSampleViewModelFactory viewModelFactory;
    private CalculatorViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CalculatorViewModel.class);
        viewModel.test();
    }
}
