package me.vannen.mvvmsample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import me.vannen.mvvmsample.R;
import me.vannen.mvvmsample.ui.calculator.CalculatorFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutMain, new CalculatorFragment())
                    .commit();
        }
    }
}
