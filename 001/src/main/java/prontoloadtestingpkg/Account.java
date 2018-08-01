package prontoloadtestingpkg;

/**
 * The account class holds details of the basic operator object as POJO type class. This provides an entity class that can be used later for test data.
 * @author Rob Allan DX Solutions
 * @version 0.1
 */

public class Account {
	private String name = "";
	private String Id = "";

	/**
	 * This is a constructor method for the account. 
	 * @param name - pass in a name associated with the account
	 * @param id - pass in an id associated with the account
	 */
	
	public Account(String name, String id) {
		super();
		this.name = name;
		Id = id;
	}
	
	/**
	 * A getter method for the account name
	 * @return name name as String
	 */
	
	public String getName() {
		return name;
	}
	
	/** A setter method for the name
	 * @param name the name
	*/
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * A getter method for the id
	 * @return id id as a string
	 */
	
	public String getId() {
		return Id;
	}
	
	/**
	 * A setter method for the id
	 * @param id id of the account 
	 */
	
	public void setId(String id) {
		Id = id;
	}
}
