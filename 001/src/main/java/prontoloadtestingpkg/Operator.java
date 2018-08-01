package prontoloadtestingpkg;

/**
 * The operator class holds details of the basic operator object as POJO type class. This provides an entity class that can be used later for test data.
 * @author Rob Allan DX Solutions
 * @version 0.1
 */

public class Operator {

	private String name  = "";
	private String Id = "";
	
	/**
	 * This is a constructor method for the operator. 
	 * @param name pass in a name associated with the operator
	 * @param id pass in an id associated with the operator
	 */ 
	
	public Operator(String name, String id) {
		super();
		this.name = name;
		Id = id;
	}

	
	/**
	 * A getter method for the name
	 * @return name name as a string
	 */
	
	public String getName() {
		return name;
	}
	
	
	/**
	 * A setter method for the name
	 * @param name the name of the operator 
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * A getter method for the id
	 * @return id as a string
	 */	
	
	public String getId() {
		return Id;
	}
	
	/**
	 * A setter method for the id
	 * @param id the id of the operator
	 */
	
	public void setId(String id) {
		Id = id;
	}	
}

