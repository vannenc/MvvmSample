package me.vannen.mvvmsample.ui.calculator;

import me.vannen.mvvmsample.RxSchedulersOverrideRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CalculatorViewModelTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    private CalculatorViewModel calculatorViewModel;
    private CalculatorViewModel.ClickCallBacks callbacks;

    @Before
    public void setUp() {

        callbacks = Mockito.mock(CalculatorViewModel.ClickCallBacks.class);
        calculatorViewModel = new CalculatorViewModel();
        calculatorViewModel.setCallback(callbacks);
    }

    @Test
    public void testCorrectTotal() {
        calculatorViewModel.calculator.number1.set("10");
        calculatorViewModel.calculator.number2.set("10");
        calculatorViewModel.calculator.number3.set("10");
        calculatorViewModel.calculator.number4.set("10");
        calculatorViewModel.calculator.number5.set("10");
        calculatorViewModel.calculator.number6.set("10");

        assertEquals("60.0", calculatorViewModel.calculator.total.get());
    }

    @Test
    public void testIncorrectTotal() {
        calculatorViewModel.calculator.number1.set("rrr");
        calculatorViewModel.calculator.number2.set("10");
        calculatorViewModel.calculator.number3.set("10");
        calculatorViewModel.calculator.number4.set("10");
        calculatorViewModel.calculator.number5.set("10");
        calculatorViewModel.calculator.number6.set("1dsfdsf");

        assertEquals("Error", calculatorViewModel.calculator.total.get());
    }

    @Test
    public void testTotalClick() {
        calculatorViewModel.onTotalClick();
        Mockito.verify(callbacks).onTotalClick();
    }

    @Test
    public void testTotalToggle() {
        calculatorViewModel.toggleTotalVisible();
        assertEquals(false, calculatorViewModel.calculator.totalVisble.get());
    }
}
