package prontoloadtestingpkg;

//This is a prototype class that is not used in the main running of the Pronto Loader 

import org.openqa.selenium.By;
import org.openqa.selenium.winium.WiniumDriver;
public class InitialSingleThreadPrototype {
	private WiniumDriver app;
	
	// Basic constructor to create a sngle therad and pass a winium driver to it 
	
	public InitialSingleThreadPrototype(WiniumDriver app) {
		this.app = app;
	}
	
	// method to add two integers, calls an internal method to execute the appropriate add operation (by pressing the correct button) and return a result. 
	
	protected int add(int firstNumber, int secondNumber) {
		return compute("Plus", firstNumber, secondNumber);
	}
	
	// method to subtract two integers, calls an internal method to execute the appropriate subtract operation (by pressing the correct button) and return a result. 
	protected int subtract(int firstNumber, int secondNumber) {
		return compute("Minus", firstNumber, secondNumber);
	}
	
	// method to subtract two integers, calls an internal method to execute the appropriate multiply operation (by pressing the correct button) and return a result. 
	protected int multiply(int firstNumber, int secondNumber) {
		return compute("Multiply by", firstNumber, secondNumber);
	}
	
	// method to divide integers by another, calls an internal method to execute the appropriate divide operation (by pressing the correct button) and return a result. 
	protected int divide(int firstNumber, int secondNumber) {
		return compute("Divide by", firstNumber, secondNumber);
	}
	
	/* Private method that is used to take 2 numbers, use the appropriate operator (+ - * etc), and then find the result and return the calling method. 
	*  Closes the calculator instance at the end of the test
	*/
	
	private int compute(String type, int firstNumber, int secondNumber) {
		app.findElementByName(convert(firstNumber)).click();
		app.findElementByName(type).click();
		app.findElementByName(convert(secondNumber)).click();
		app.findElementByName("Equals").click();
		String results1 = app.findElementById("CalculatorResults").getAttribute("Name");
		Double result = Double.parseDouble(results1.substring(11).trim());
		app.findElement(By.id("Close")).click();
		return result.intValue();
	}
	
	// private array to translate a number to a name that can be sued to iendtify a button.
	private static final String[] numNames = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",	"Nine" };
	
	// Simple lookup method on a array of numbers to convert to textual strings - the textual string being used to identify the buttons.
	public String convert(int number) {
		return numNames[number];
	}
}
