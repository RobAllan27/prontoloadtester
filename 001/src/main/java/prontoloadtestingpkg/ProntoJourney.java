package prontoloadtestingpkg;

import org.openqa.selenium.winium.WiniumDriver;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;


/**
 * The pronto journey is an abstract class that. It has a defined constructor to pass in the winium drover and arrays of test data.
 * 
 * It provides base method to obtain arrays of test data, as well as providing a random access lookup to get a random entry.
*  It also has a delay method for holding up the execution of any specific journey.  
*  
 * @author Rob Allan DX Solutions
 * @version 0.1
 */

public abstract class ProntoJourney {

	protected WiniumDriver winiumDriverApp;
	private HashMap<String, String> threadProperties;
	private String journeyName;
	private ArrayList<Operator> operatorList;
	private ArrayList<Account> accountList;
	private ArrayList<Part> partList;
	private Random randomGenerator;
	private int index; // for the collections searches
	
	/** A constructor method for the abstract class. Allows the winium diver applications to be passed, as well as the thread properties and a datamap of the various types of entities.
	 * @param inWiniumDriverApp winium diver applications to be passed
	 * @param inthreadProperties thread properties
	 * @param inDataMap datamap of the various types of entities accounts, parts etc
 	*/
	
	public ProntoJourney(WiniumDriver inWiniumDriverApp, HashMap<String, String> inthreadProperties, HashMap<String, ArrayList> inDataMap) {
		winiumDriverApp = inWiniumDriverApp;
		threadProperties = inthreadProperties;
		journeyName = threadProperties.get("JourneyName");
		findDataSets("operators", inDataMap);
		findDataSets("accounts", inDataMap);
		findDataSets("parts", inDataMap);
      randomGenerator = new Random();
	}
	
	/** An abstract method for the execute tests - concrete implementations should extend this class and this is the pivotal method - where they define their actual behaviour
	 * No parameters
	*/
	
	public abstract void executeTests();
	
	// vary this method to set the various different arraylists for the testing - these will all hold data-generic builder method that will allow us to extract data   
	
	/** A private method to obtain a data set - depends on the type of data set requested
	 * @param typeOfArrayListToFound - the type of data to be requested  -  e.g. operators, accounts etc..
	 * @param inDataMap - the datamap contains a set of array lists -  
	*/
	
	private void findDataSets(String typeOfArrayListToFound, HashMap<String, ArrayList> inDataMap)
	{
		ArrayList arrayListToBeFound = inDataMap.get(typeOfArrayListToFound);
		
	     switch (typeOfArrayListToFound) {
       case "operators":
      	 operatorList = (ArrayList<Operator>)arrayListToBeFound;
           break;    
       case "accounts":
      	 accountList = (ArrayList<Account>)arrayListToBeFound;
           break;
       case "parts":
   		partList = (ArrayList<Part>)arrayListToBeFound;
           break;   
       default:
           throw new IllegalArgumentException("Invalid Journey Type supplied to build a array of data");
   }				// now we will decide which array
	}
	
	/** A method to obtain a random operator (as in UI operator) - from the imported set of operators (passed as in set of array lists).
	 * @return operator - return an instance of a part.
	*/
	protected Operator getRandomOperator() {	
			Operator op;
      	index = randomGenerator.nextInt(operatorList.size());
      	op = operatorList.get(index);
		return op;
	}
	
	
	/** A method to obtain a random account (as in customer account) - from the imported set of accounts (passed as in set of array lists)..
	 * @return account - return an instance of a part.
	*/ 
	protected Account getRandomAccount() {	
		Account acct;
  	index = randomGenerator.nextInt(accountList.size());
      acct = accountList.get(index);
	return acct;
}
	
	
	/** A method to obtain a random part (as in component part).
	 * @return part - return an instance of a part.
	*/
	protected Part getRandomPart() {	
		Part pt;
  	index = randomGenerator.nextInt(partList.size());
      pt = partList.get(index);
	return pt;
}
	
	/** A delay method for the name. Takes as an argument a name of the delay, which will be in the thread properties hashmap, and then executes the delay.
	 * @param delayname - the name of the delay
	*/
	// method to execute delay in - can be reused in any journey. 
	protected void executeCurrentDelay(String delayName){
		int currentDelay = Integer.parseInt(threadProperties.get(delayName));
		if (currentDelay == 0){
			return;
		}
		else{
		try{
			Thread.sleep(currentDelay);
		}
		catch(Exception e)
		{
			System.out.println("This Journey stopped working when delayed - Journey Name " + this.journeyName + " - with delay " + currentDelay);	
		}
		}
	}
}	
	
