package prontoloadtestingpkg;

//This is a prototype class that is not used in the main running of the Pronto Loader 

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
public class OldProntoDriver {
    private WiniumDriver app = null;
    private DesktopOptions options;
    private String CALC_PATH = "C:\\Windows\\System32\\calc.exe";
    private String WINIUM_PATH = "http://localhost:9999";
    private InitialSingleThreadPrototype calc;

    @Before
    public void startCalculator() throws MalformedURLException {
        options = new DesktopOptions();
        options.setApplicationPath(CALC_PATH);
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        app = new WiniumDriver(new URL(WINIUM_PATH), options);
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        calc = new InitialSingleThreadPrototype(app);
    }

    @Test
    public void add() {
        assertThat(calc.add(7, 5), is(12));
    }

    @Test
    public void subtract() {
        assertThat(calc.subtract(7, 5), is(2));
    }

    @Test
    public void multiply() {
        assertThat(calc.multiply(7, 5), is(35));
    }

    @Test
    public void divide() {
        assertThat(calc.divide(7, 5), is(1));
    }
}
