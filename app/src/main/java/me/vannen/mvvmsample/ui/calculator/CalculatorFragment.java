package me.vannen.mvvmsample.ui.calculator;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;
import me.vannen.mvvmsample.R;
import me.vannen.mvvmsample.databinding.FragmentCalculatorBinding;
import me.vannen.mvvmsample.di.MvvmSampleViewModelFactory;
import timber.log.Timber;

public class CalculatorFragment extends DaggerFragment implements CalculatorViewModel.ClickCallBacks {

    @Inject MvvmSampleViewModelFactory viewModelFactory;
    private CalculatorViewModel viewModel;
    private FragmentCalculatorBinding dataBinding;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator, container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CalculatorViewModel.class);
        viewModel.setCallback(this);
        dataBinding.setViewModel(viewModel);
    }

    @Override
    public void onTotalClick() {
         Timber.i("Total clicked");
    }
}
