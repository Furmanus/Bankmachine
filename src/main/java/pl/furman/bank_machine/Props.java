/**
 * Class representing properties used to establish connection to database by JDBC. Has one private field, Properties object which has three properties: host, username and password
 * @author Łukasz Lach
 */

package pl.furman.bank_machine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.AccessControlContext;
import java.util.Properties;

public class Props {

	private Properties access;
	
	/**
	 * Public constructor which created ObjectInputStream to read Properties from file "access.properties". Properties are stored in access private
	 * field.
	 * @exception FileNotFoundException, IOException, ClassNotFoundException
	 */
	public Props(){
		
		try {
			ObjectInputStream read = new ObjectInputStream(new FileInputStream("access.properties"));
			access = (Properties)read.readObject();
			read.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to set new properties in private access field.
	 * @param host Name of host.
	 * @param username Name of user.
	 * @param password Password
	 */
	public void setProps(String host, String username, String password){
		
		access.setProperty("host", host);
		access.setProperty("username", username);
		access.setProperty("password", password);
	}
	
	/**
	 * Getter for access private field.
	 * @return Properties Returns private Properties access object.
	 */
	public Properties getProps(){
		
		return this.access;
	}
}
