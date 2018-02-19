package me.vannen.mvvmsample.ui.calculator;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dagger.android.support.DaggerFragment;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import me.vannen.mvvmsample.R;
import me.vannen.mvvmsample.databinding.FragmentCalculatorBinding;
import me.vannen.mvvmsample.di.MvvmSampleViewModelFactory;
import timber.log.Timber;

public class CalculatorFragment extends DaggerFragment
        implements CalculatorViewModel.ClickCallBacks {

    @Inject MvvmSampleViewModelFactory viewModelFactory;
    private CalculatorViewModel viewModel;
    private FragmentCalculatorBinding dataBinding;
    private final Handler handler = new Handler();
    private boolean totalIsBlinking;
    private Disposable disposableTotalVisibility;
    private Runnable runnableTotalVisibility = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 500);
            viewModel.toggleTotalVisible();
        }
    };

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
    public void onPause() {
        super.onPause();

        if (disposableTotalVisibility != null && !disposableTotalVisibility.isDisposed()) {
            disposableTotalVisibility.dispose();
        }

        if (runnableTotalVisibility != null) {
            handler.removeCallbacks(runnableTotalVisibility);
        }
    }

    @Override
    public void onTotalClick() {
        Timber.i("Total clicked");

        if (totalIsBlinking) {
            return;
        }

        totalIsBlinking = true;
        if (System.currentTimeMillis() % 2 == 0) {
            Timber.i("Running handler method");
            handler.post(runnableTotalVisibility);
        } else {
            Timber.i("Running rxjava method");
            disposableTotalVisibility = Observable.interval(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o1 -> viewModel.toggleTotalVisible());
        }
    }
}
