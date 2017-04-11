package pl.furman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import pl.furman.bank_machine.Account;
import pl.furman.bank_machine.Props;
import pl.furman.interfaces.DatabaseInterface;

/**
 * Class used to establish connection with database via JDBC.
 * @author Docent Furman
 */
public class DataBaseJDBC implements DatabaseInterface{

	private static Properties access = new Props().getProps();
	
	/**
	 * Method used to retrieve Account object (identified by unique number) from database.
	 * @param number Unique String number identifier of account.
	 * @return Account Returns Account object.
	 * @exception ClassNotFoundException, SQLException.
	 */
	public Account getAccountByNumber(String number){
		
		Account account = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			
			Connection connect = DriverManager.getConnection(access.getProperty("host"), access.getProperty("user"), access.getProperty("password"));
			PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM accounts WHERE number = ?");
			pstmt.setString(1, number);
			
			ResultSet result = pstmt.executeQuery();
			
			if(result.next()){
			
				account = new Account(result.getInt("balance"), result.getString("number"), result.getString("pin"), result.getString("owner"), this);
			}
			
			result.close();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return account;
	}
	
	/**
	 * Method used to verify whether account with given number exists in database.
	 * @param number String number of account.
	 * @return boolean Returns true if account with given number exists, returns false otherwise.
	 * @exception ClassNotFoundException, SQLException.
	 */
	public boolean verifyAccountNumber(String number){
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connect = DriverManager.getConnection(access.getProperty("host"), access.getProperty("user"), access.getProperty("password"));
			PreparedStatement pstmt = connect.prepareStatement("SELECT number FROM accounts WHERE number = ?");
			pstmt.setString(1, number);
			
			ResultSet result = pstmt.executeQuery();
			
			if(result.next()){
				
				if(result.getString("number") == "null"){
					
					result.close();
					return false;
				}else{
					
					result.close();
					return true;
				}
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method used to change balance of Account object by certain Integer amount
	 * @param account Account object which balance has to be changed
	 * @param amount Integer number by which Account object balance has to be modified.
	 * @return boolean Returs true if operation was successful, returns false otherwise.
	 * @exception ClassNotFoundException, SQLException.
	 */
	public boolean changeBalance(Account account, int amount){
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connect = DriverManager.getConnection(access.getProperty("host"), access.getProperty("user"), access.getProperty("password"));
			int updatedAmount = account.getBalance() + amount;
			PreparedStatement pstmt = connect.prepareStatement("UPDATE accounts SET balance = ?" + " WHERE owner = ?");
			pstmt.setInt(1, updatedAmount);
			pstmt.setString(2, account.getOwner());
			
			pstmt.execute();
			
			pstmt.close();
			
			return true;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
}
