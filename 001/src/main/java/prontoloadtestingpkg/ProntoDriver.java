package prontoloadtestingpkg;

/**
 * The pronto driver class provides the main method to invoke a stream of load testing
 * 
 * This is the main runner class. It supports a single method main. This will create an new despatcher, whose role it is to co-ordinate and despatch a set of threads. 
 * It also get the despatcher to build up a set of properties that are used to guide the execution.
 * @author Rob Allan DX Solutions
 * @version 0.1
 */

public class ProntoDriver {

	/**
	 * A main method for the account name
	 * No parameters.
	 * @param args will be null here
	 * Sets up a despatcher
	 * Gets the despatcher to build values
	 * Get the despatcher to drive tests
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProntoTestDespatcher pjd = new ProntoTestDespatcher();
		pjd.setUpPropertiesValues();
		pjd.driveTest();
	}
}
