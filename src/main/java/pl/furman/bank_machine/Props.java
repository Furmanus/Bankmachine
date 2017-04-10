package pl.furman.bank_machine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.AccessControlContext;
import java.util.Properties;

public class Props {

	private Properties access;
	
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
	
	public void setProps(String host, String username, String password){
		
		access.setProperty("host", host);
		access.setProperty("username", username);
		access.setProperty("password", password);
	}
	
	public Properties getProps(){
		
		return this.access;
	}
}
