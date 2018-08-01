package prontoloadtestingpkg;

/**
 * The part class holds details of the basic operator object as POJO type class. This provides an entity class that can be used later for test data.
 * @author Rob Allan DX Solutions
 * @version 0.1
 */

public class Part {
	private String name = "";
	private String iD = "";
	
	//This is a POJO class that is used to hold details of a part. 
	
	/**
	 * This is a constructor method for the part. 
	 * @param name pass in a name associated with the part.
	 * @param iD pass in an id associated with the part.
	 */ 
	
	public Part(String name, String iD) {
		super();
		this.name = name;
		this.iD = iD;
	}
	
	
	/**
	 * A getter method for the name
	 * @return name  - as a string
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * A setter method for the name
	 * @param name the name of the part
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * A getter method for the iD
	 * @return iD - the id of the part
	 */
	
	public String getiD() {
		return iD;
	}
	
	/**
	 * A setter method for the id
	 * @param iD the id of the part
	 */
	
	public void setiD(String iD) {
		this.iD = iD;
	}
}