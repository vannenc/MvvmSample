package me.vannen.mvvmsample.ui.calculator;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Cancellable;
import me.vannen.mvvmsample.vo.Calculator;
import timber.log.Timber;

public class CalculatorViewModel extends ViewModel {

    public static final String GENERIC_ERROR = "Error";
    private Calculator calculator = new Calculator();

    public CalculatorViewModel() {
        io.reactivex.Observable.combineLatest(toObservable(calculator.number1), toObservable(calculator.number2), toObservable(calculator.number3), toObservable(calculator.number4), toObservable(calculator.number5), toObservable(calculator.number6), (s, s2, s3, s4, s5, s6) -> {
            float newTotal = 0;

            try {
                newTotal += Float.parseFloat(s)
                        + Float.parseFloat(s2)
                        + Float.parseFloat(s3)
                        + Float.parseFloat(s4)
                        + Float.parseFloat(s5)
                        + Float.parseFloat(s6);

                calculator.total.set(String.valueOf(newTotal));
                Timber.i("New total: %s", newTotal);
            } catch (NumberFormatException e) {
                Timber.i("Error: %s", GENERIC_ERROR);
                return GENERIC_ERROR;
            }

            return String.valueOf(newTotal);
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newTotal -> calculator.total.set(newTotal), throwable -> calculator.total
                        .set(GENERIC_ERROR));
    }

    public Calculator getCalculator() {
        return calculator;
    }

    // Copied from http://manaschaudhari.com/blog/2016/09/05/rxjava-meets-data-binding-part-4/

    @NonNull
    public static <T> io.reactivex.Observable<T> toObservable(
            @NonNull final ObservableField<T> field) {

        return io.reactivex.Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> e) throws Exception {
                T initialValue = field.get();
                if (initialValue != null) {
                    e.onNext(initialValue);
                }
                final Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(android.databinding.Observable observable, int i) {
                        e.onNext(field.get());
                    }
                };
                field.addOnPropertyChangedCallback(callback);
                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        field.removeOnPropertyChangedCallback(callback);
                    }
                });
            }
        });
    }
}
