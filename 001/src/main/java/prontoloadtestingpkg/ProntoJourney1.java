package prontoloadtestingpkg;



import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.WiniumDriver;

/**
 * The pronto journey class holds details of the specifics for this journey - common functions are in the abstract super class Pronto Journey
 * @author Rob Allan DX Solutions
 * @version 0.1
 */

public class ProntoJourney1 extends ProntoJourney{
	
	/** The constructor has an argument for the remote winium driver, for the thread properties, and a hashmap of arraylists for the test data.
	 * 
	 * The constructor is defined here as there is no null argument constructor in the super class.
	 * @param inWiniumDriverApp winium diver applications to be passed
	 * @param inthreadProperties thread properties
	 * @param inDataMap datamap of the various types of entities accounts, parts etc
	 * 
	*/
	
	public ProntoJourney1(WiniumDriver inWiniumDriverApp, HashMap<String, String> inthreadProperties, HashMap<String, ArrayList> inDataMap) {
		super(inWiniumDriverApp, inthreadProperties, inDataMap);
	}

	
	/** The actual method to execute the tests - this will then call the specific private methods to execute steps. It also populates a random set of elements (such as parts, operators and accounts)
	 * that can be used later in the thread. 
	 * No parameters
	*/
	
	public void executeTests(){	
		executeCurrentDelay("InitialDelay");  //TODO to add the initial delay 
		
		loginToScreen();
		Operator op = this.getRandomOperator();
		Part pt = this.getRandomPart();
		Account acct = this.getRandomAccount();
		winiumDriverApp.findElement(By.id("Close")).click();
	}


	/** private method that actually executes a step  - the login step - more steps then get implemented
	 * @param - none
	 * 
	*/
	private void loginToScreen() {;
		CharSequence UserNameCS = "peter_gaston123";
		CharSequence PasswordCS = "****";
		//System.out.println("Some username text " + winiumDriverApp.findElementByName("User name:").getText());
		//winiumDriverApp.findElementByName("User name:").clear();
		//winiumDriverApp.findElementByName("User name:").sendKeys(UserNameCS);
		//System.out.println("Some password text " + winiumDriverApp.findElementByName("Password:").getText());
		
		try{ 
		
		//winiumDriverApp.findElementById("61255").clear();
		WebElement username = winiumDriverApp.findElementByName("User name:");
		username.getText();
		
		System.out.println(username.getTagName() + " Text +++ " + username.getText()) ;
		winiumDriverApp.findElementByName("User name:").clear();
		this.executeCurrentDelay("pj1-loginScreenDelay");
		
		//winiumDriverApp.findElementById("61255").sendKeys(UserNameCS);
		winiumDriverApp.findElementByName("User name:").sendKeys(UserNameCS);
		this.executeCurrentDelay("pj1-loginScreenDelay");
		
		WebElement password = winiumDriverApp.findElementByName("Password:");
		//winiumDriverApp.findElementById("61254").clear();
		winiumDriverApp.findElementByName("Password:").clear();
		this.executeCurrentDelay("pj1-loginScreenDelay");
		
		//winiumDriverApp.findElementById("61254").sendKeys(PasswordCS);
		winiumDriverApp.findElementByName("Password:").sendKeys(PasswordCS);
		this.executeCurrentDelay("pj1-loginScreenDelay");
		/*
		winiumDriverApp.findElementByName("Password:").clear();
		winiumDriverApp.findElementByName("Password:").sendKeys(PasswordCS);
		
		*/
		
		winiumDriverApp.findElementByName("Login").click();
		
		//winiumDriverApp.findElement(By.id("Close")).click();
		System.out.println("We have pressed buttons");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}