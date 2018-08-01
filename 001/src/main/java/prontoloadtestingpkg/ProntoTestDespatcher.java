package prontoloadtestingpkg;



import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class is used to dispatch a set of threads. It also parses files to build up sets of test data - initial set of 'operators, parts and accounts' but more can be easily added.
 * @author Rob Allan DX Solutions
 * @version 0.1
 */ 

public class ProntoTestDespatcher {

	private HashMap<String, String> threadProperties;
	private HashMap<String, ArrayList> readDataMap;
	private String pathToAccountsFile = "C:\\Users\\Rob.Allan\\Downloads\\rexelperformancetester\\datafiles\\accounts\\accounts.txt";
	private String pathToOperatorsFile = "C:\\Users\\Rob.Allan\\Downloads\\rexelperformancetester\\datafiles\\operators\\operators.txt";
	private String pathToPartsFile = "C:\\Users\\Rob.Allan\\Downloads\\rexelperformancetester\\datafiles\\parts\\parts.txt";
	private ArrayList<Account> accounts;
	private ArrayList<Operator> operators;
	private ArrayList<Part> parts;

	
	/**
	 * This public method to set up appropriate values across all the streams - main place to store values.
	 * The method also calls the private method readInTestData, to set up test data for operators, accounts and parts
	 * No parameters
	 */
	
	public void setUpPropertiesValues(){
		threadProperties = new HashMap<String, String>();
		threadProperties.put("winiumPort","9999");
		threadProperties.put("InitialDelay","5000");
		//threadProperties.put("JourneyName","1st Journey");
		threadProperties.put("preWiniumCreateDelay","5000"); 
		threadProperties.put("postWiniumCreateDelay","5000");
		//threadProperties.put("typeOfJourney","ProntoJourney1");
		threadProperties.put("pj1-delay1","5000"); 
		threadProperties.put("pj1-delay2","5000");
		threadProperties.put("pj1-loginScreenDelay","100");
		
		readInTestdata();
	}
	
	/** Private method to read test data in. Calls the subsequent supporter method readdatafromTxt() to get the data from text files.
	 * 
	 *  In this method, we will we populate the test data and then make it available for the various threads.
	 *  
	 *  this will read the various different data sources
	*	 - Accounts
	*	 - Operators
	*	 - Parts
	 *	It creates a set of arraylists that will store the various types of test data, and support random access to that data.
	 *  @param - none
	*/
	
	private void readInTestdata() {

		
		accounts = new ArrayList<>();
		operators = new ArrayList<>();
		parts = new ArrayList<>();
		
		readDataFromTxt("accounts",pathToAccountsFile, accounts);
		readDataFromTxt("operators",pathToOperatorsFile, operators);
		readDataFromTxt("parts",pathToPartsFile, parts);
		
		readDataMap = new HashMap<>(); 
		
		readDataMap.put("accounts",accounts);
		readDataMap.put("parts",parts);
		readDataMap.put("operators",operators);
	}
	
	
	/**  This private method is used to import data from files - it allows data to be obtained from files, and updates an array list.. 
	 * @param dataType - the data type - drives what type of objects will be created in the array lists - Operators, Parts or Accounts for example 
	 * @param fileName- the file where the data should be obtained from
	 * @param inArrayList  - the arraylist where data will be stored
	*/
	
	//
	private void readDataFromTxt(String dataType, String fileName, ArrayList inArrayList) { 

		Path pathToFile = Paths.get(fileName);

		// create an instance of BufferedReader 

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) 
		{ 
			// read the first line from the text file 
			String line = br.readLine(); 

			// loop until all lines are read 
			String String1 = "";
			String String2 = "";
			String String3 = "";

			while (line != null) { 
				// use string.split to load a string array with the values from // each line of // the file, using a comma as the delimiter 
				String[] attributes = line.split(","); 

				// being a bit lazy here I will assume dangerous that we have 3 attributes in the file.

				String1 = attributes[0];
				String2 = attributes[1];
				String3 = attributes[2];

				switch (dataType) {
				case "operators":
					Operator operator = new Operator(String1, String2);
					operators.add(operator);
					break;    
				case "accounts":
					Account account = new Account(String1, String2);
					accounts.add(account);
					break;
				case "parts":
					Part part = new Part(String1, String2);
					parts.add(part);
					break;
				default:
					throw new IllegalArgumentException("Invalid Journey Type supplied to build a array of data");
				}				// now we will decide which array 
			// read next line before looping 
			// if end of file reached, line would be null 
			line = br.readLine(); 
		}
		} catch (IOException ioe) 
		{ 
			ioe.printStackTrace(); 
		}		
}
	
	/** This method, drivetest() actually sets the various threads running - and we can set how many instances we want to run - we use run here to choose to not run in a threaded manner i.e. single threaded
	8 replace with run to make this run in threaded manner
	 * No parameters
	*/
	
	public void driveTest(){
		
		// this will derive the total number of threads of any one type
		int pj1count = 1; // let's just create 1 at this stage
		int pj2count = 0;
		int pj3count = 0;
		int pj4count = 0;
		ProntoTestingThread pjt;

		// we start these so that they will use a different Winium port 
		int pjt1_thread_used_port = 8000;
		int pjt2_thread_used_port = 8200;
		int pjt3_thread_used_port = 8400;
		int pjt4_thread_used_port = 8600; 
		
		//threadProperties.put("typeOfJourney","ProntoJourney1");
		for (int i = 0; i < pj1count;i ++) { 
			pjt = new ProntoTestingThread();
			pjt.setThreadProperties(threadProperties, readDataMap, "ProntoJourney1", pjt1_thread_used_port);
			pjt.runhere();
			pjt1_thread_used_port++;
			//TO DO - to make multi threaded
			//pjt.start(); //JVM will call the run method
		}
		for (int i = 0; i < pj2count;i ++) { 
			pjt = new ProntoTestingThread();
			pjt.setThreadProperties(threadProperties, readDataMap, "ProntoJourney2", pjt2_thread_used_port);
			pjt.runhere(); //TO DO - to make multi threaded
			pjt2_thread_used_port++;
			//pjt.start(); //JVM will call the run method
		}
		for (int i = 0; i < pj3count;i ++) { 
			pjt = new ProntoTestingThread();
			pjt.setThreadProperties(threadProperties, readDataMap, "ProntoJourney3", pjt3_thread_used_port);
			pjt.runhere(); //TO DO - to make multi threaded
			pjt3_thread_used_port++;
			//pjt.start(); //JVM will call the run method
		}
		for (int i = 0; i < pj4count;i ++) { 
			pjt = new ProntoTestingThread();
			pjt.setThreadProperties(threadProperties, readDataMap, "ProntoJourney4", pjt4_thread_used_port);
			pjt.runhere();
			pjt4_thread_used_port++;//TO DO - to make multi threaded
			//pjt.start(); //JVM will call the run method
		}
	}
}
