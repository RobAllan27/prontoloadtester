package prontoloadtestingpkg;


import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The pronto testing thread extends thread class. When it is constructed it will have have the parameters for the thread in the properties, a reference to data maps, the type of journey to be executed and the port on which it is to be executed  
 * The run method (which may be refactored to make it truly runnable) will start winium and then start a journey - it will choose the appropriate journey based on the passed in journey type. 
 * @author Rob Allan DX Solutions
 * @version 0.1
 */

public class ProntoTestingThread extends Thread {

    private WiniumDriver winiumDriverApp = null;
    private DesktopOptions options;
    //private String CALC_PATH = "C:\\Windows\\System32\\calc.exe";
    private String PRONTO_PATH ="C:\\Program Files (x86)\\PRONTO\\PROCLIENT\\proclient.exe";
    //private String PRONTO_PATH = "C:\\Windows\\System32\\calc.exe";
    private String WINIUM_PATH = "http://localhost:";
    //private ProntoJourney prontoJourn;
	private int winiumPort;
	private HashMap<String, String> threadProperties;
	private HashMap<String, ArrayList> dataMap;
	private String journeytype;
	
	/** A setThreadPoperties method  - this could have been done in a constructor but deferred to separate method.
	 * Several parameters - it will have have the parameters for the thread in the properties, a reference to data maps, the type of journey to be executed and the port on which it is to be executed  
	 * @param inThreadProperties hashmap or thread properties
	 * @param inDataMap a set of data (accounts operators, parts etc)
	 * @param inTypeOfJourney type of journey
	 * @param inWiniumPort port number for the instance of winium
	*/
	
	public void setThreadProperties(HashMap <String,String> inThreadProperties, HashMap <String, ArrayList> inDataMap, String inTypeOfJourney, int inWiniumPort){
		this.threadProperties = inThreadProperties;
		this.dataMap = inDataMap;
		journeytype = inTypeOfJourney;
		this.winiumPort = inWiniumPort;
	}	
	
	/** A method that actually starts the thread - this method may be refactored to make this genuinely multi-threaded -  hint simply change the method name to run().
	 *  This method will start winium and then start a journey - it will choose the appropriate journey based on the passed in journey type. 
	 * No parameters
	*/
	
	public void runhere(){  
		System.out.println("thread is running...");
		//winiumPort = Integer.parseInt(threadProperties.get("winiumPort")); //TODO to add the winiumPort
		WINIUM_PATH = WINIUM_PATH + winiumPort;
		startWinium();
		startJourney();
		} //TO DO to remove the runhere() and use a basic run method... 
	
	/** A private method to start winium, pass the actual path where the system under test lies
	 * @param name - none
	*/
	
	private void startWinium(){		
        options = new DesktopOptions();
        options.setApplicationPath(PRONTO_PATH);
       //options.setApplicationPath(CALC_PATH);
       int preWiniumCreateDelay = Integer.parseInt(threadProperties.get("preWiniumCreateDelay")); //TODO to add the preWiniumCreateDelay
       int postWiniumCreateDelay = Integer.parseInt(threadProperties.get("postWiniumCreateDelay")); //TODO to add the postWiniumCreateDelay 
        try {
			Thread.sleep(preWiniumCreateDelay);
		} catch (InterruptedException e) {
			//this is just to delay
			e.printStackTrace();
		}
        
        // now we create
        try{
        	winiumDriverApp = new WiniumDriver(new URL(WINIUM_PATH), options);
        	}
        catch (MalformedURLException mfue)
        {
        	System.out.println("Could not get to Winium " + WINIUM_PATH);
        }
        
        try {
			Thread.sleep(postWiniumCreateDelay);
		} catch (InterruptedException e) {
			//this is just to delay
			e.printStackTrace();
		}
	}
	
	/** This will start a journey - it will choose the appropriate journey based on the passed in journey type. 
	 * It will pass the winium driver application, the thread properties for the journey to use and the datamap for the stored data.   
	 * @param name - none
	*/
	
	private void startJourney(){
		
		ProntoJourney pj =  null;
		
		//extend the number of cases below here when new journeys are provided..
		
	     switch (journeytype) {
         case "ProntoJourney1":
        	 pj = new ProntoJourney1(winiumDriverApp, threadProperties, dataMap);
             break;    
         // prototype code for the new journeys should we want them in future
         case "ProntoJourney2":
        	 //pj = new ProntoJourney2(winiumDriverApp, threadProperties);
             break;
         case "ProntoJourney3":
        	 //pj = new ProntoJourney3(winiumDriverApp, threadProperties);
             break;
         case "ProntoJourney4":
        	 //pj = new ProntoJourney4(winiumDriverApp, threadProperties);
             break;    
         default:
             throw new IllegalArgumentException("Invalid Journey Type supplied: " + journeytype);
     }		
		if (pj != null){
			pj.executeTests();
		} 
	}
}