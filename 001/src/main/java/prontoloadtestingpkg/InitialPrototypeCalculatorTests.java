package prontoloadtestingpkg;

// This is a prototype class that is not used in the main running of the Pronto Loader 


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
public class InitialPrototypeCalculatorTests {
    private WiniumDriver app = null; // a winuim applications
    private DesktopOptions options;
    private String CALC_PATH = "C:\\Windows\\System32\\calc.exe"; // location of the application to run
    private String WINIUM_PATH = "http://localhost:9999"; // location of where to send winium commands to - get proxied to the application
    private InitialSingleThreadPrototype calc;

    
    // Before method called. we build a set of options, set the paths to the application and the proxy. 
    // There are some simple sleep statements here, before we build,the winium application
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

    // an add test to check the values received.
    @Test
    public void add() {
        assertThat(calc.add(7, 5), is(12));
    }

    // a subtract test to test the valeus received
    @Test
    public void subtract() {
        assertThat(calc.subtract(7, 5), is(2));
    }

    // a multiply test to test the values received
    @Test
    public void multiply() {
        assertThat(calc.multiply(7, 5), is(35));
    }

    // a divide test to test the values received
    @Test
    public void divide() {
        assertThat(calc.divide(7, 5), is(1));
    }
}
