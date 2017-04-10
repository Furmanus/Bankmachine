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
	
	public Account(){
		
	}
	
	public Account(int balance, String number, String pin, String owner, DatabaseInterface databaseAccess) {
		
		this.balance = balance;
		this.number = number;
		this.pin = pin;
		this.owner = owner;
		this.databaseAccess = databaseAccess;
	}
	
	public int getId(){
		return id;
	}

	public int getBalance() {
		return balance;
	}

	public String getNumber() {
		return number;
	}

	public String getPin() {
		return pin;
	}

	public String getOwner() {
		return owner;
	}
	
	public void changeBalance(int amount) throws WithdrawException, AddMoneyException{
		
		if(amount < 1 && (-amount > this.balance)){
			
			throw new WithdrawException();
		}else if(amount > 0 && amount > 500){
			
			throw new AddMoneyException();
		}
		
		this.balance += amount;
		addLog(amount);
	}
	
	public void withdrawMoney(int amount) throws WithdrawException{
		
		if(amount > this.balance || amount < 1){
			
			throw new WithdrawException();
		}
		
		databaseAccess.changeBalance(this, -amount);
	}
	
	public void addFunds(int amount) throws AddMoneyException{
		
		if(amount > 500){
			
			throw new AddMoneyException();
		}
		
		databaseAccess.changeBalance(this, amount);
	}
	
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

	public void setDatabaseAccess(DatabaseInterface databaseAccess) {
		
		this.databaseAccess = databaseAccess;
	}
}
