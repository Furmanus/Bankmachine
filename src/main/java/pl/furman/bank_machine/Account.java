/**
 * Class which objects represents rows from database
 * @author Łukasz Lach
 * @version 1.0
 */

package pl.furman.bank_machine;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import pl.furman.database.DataBaseJPA;
import pl.furman.exceptions.AddMoneyException;
import pl.furman.exceptions.WithdrawException;
import pl.furman.interfaces.DatabaseInterface;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Transient
	private DatabaseInterface databaseAccess;
	
	@Id
	private int id;

	@Column(name = "balance")
	private int balance;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "pin")
	private String pin;
	
	@Column(name = "owner")
	private String owner;
	
	/**
	 * Empty constructor used for JPA database connection. Private field of class instance are automatically filled by EclipseLink
	 * @return Nothing
	 */
	public Account(){
		
	}
	
	/**
	 * Constructor for JDBC database connection.
	 * @param balance Integer representing balance of account
	 * @param number String representing account number
	 * @param pin String representing PIN number linked to account
	 * @param databaseAccess Interface used to establish connection to database and performing operations on account
	 */
	public Account(int balance, String number, String pin, String owner, DatabaseInterface databaseAccess) {
		
		this.balance = balance;
		this.number = number;
		this.pin = pin;
		this.owner = owner;
		this.databaseAccess = databaseAccess;
	}
	/**
	 * 
	 * @return int Returns number representing unique identifier of database for
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * 
	 * @return int Returns current account balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * 
	 * @return String Returns account number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * 
	 * @return String Returns PIN number linked to account
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * 
	 * @return String Returns account owner name
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Method to change account's balance by certain amount
	 * @param amount Negative or positive integer by which account balance should be changed
	 * @throws WithdrawException
	 * @throws AddMoneyException
	 */
	public void changeBalance(int amount) throws WithdrawException, AddMoneyException{
		
		if(amount < 1 && (-amount > this.balance)){
			
			throw new WithdrawException();
		}else if(amount > 0 && amount > 500){
			
			throw new AddMoneyException();
		}
		
		this.balance += amount;
		addLog(amount);
	}
	
	/**
	 * Withdraws certain amount from account balance
	 * @param amount Integer number: amount which will be substracted from account balance
	 * @throws WithdrawException
	 */
	public void withdrawMoney(int amount) throws WithdrawException{
		
		if(amount > this.balance || amount < 1){
			
			throw new WithdrawException();
		}
		
		databaseAccess.changeBalance(this, -amount);
	}
	
	/**
	 * Adds certain amount to account balance
	 * @param amount Integer number: amount which will be added to account balance
	 * @throws AddMoneyException
	 */
	public void addFunds(int amount) throws AddMoneyException{
		
		if(amount > 500){
			
			throw new AddMoneyException();
		}
		
		databaseAccess.changeBalance(this, amount);
	}
	
	/**
	 * Method used to add logs about all operations made on account to file logs.txt. 
	 * @param amount Integer number by which account balance is modified
	 */
	private void addLog(int amount){
		
		String operation = (amount > 0) ? " added " : " withdrawn ";
		String date = new Date().toString() + "\r\n";
		
		try {
			
			FileWriter fw = new FileWriter("logs.txt", true);
			fw.write(this.owner + operation + Math.abs(amount) + " PLN. " + date);
			fw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Setter for databaseAccess object
	 * @param databaseAccess Instance of object used to establish connection to database and perform operations
	 */
	public void setDatabaseAccess(DatabaseInterface databaseAccess) {
		
		this.databaseAccess = databaseAccess;
	}
}
