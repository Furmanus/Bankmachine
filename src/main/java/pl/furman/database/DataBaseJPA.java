package pl.furman.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pl.furman.bank_machine.Account;
import pl.furman.bank_machine.Props;
import pl.furman.interfaces.DatabaseInterface;

/**
 * Class used to establish database connection via EclipseLink.
 * @author Łukasz Lach
 *
 */

public class DataBaseJPA implements DatabaseInterface{
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("bankmachine");
 	private EntityManager em = factory.createEntityManager();
	
 	/**
	 * Method used to retrieve Account object (identified by unique number) from database.
	 * @param number Unique String number identifier of account.
	 * @return Account Returns Account object.
	 */
	public Account getAccountByNumber(String number){
		
		Account account = null;
		
		Query q = em.createQuery("SELECT c FROM Account c WHERE c.number = :num");
		q.setParameter("num", number);
		account = (Account)q.getSingleResult();
		account.setDatabaseAccess(this);
		
		return account;
	}
	
	/**
	 * Method used to verify whether account with given number exists in database.
	 * @param number String number of account.
	 * @return boolean Returns true if account with given number exists, returns false otherwise.
	 * @exception NoResultException.
	 */
	
	public boolean verifyAccountNumber(String number) throws NoResultException{
		
		Account account = null;
		
		try{
			
		Query q = em.createQuery("SELECT c FROM Account c WHERE c.number = :num").setParameter("num", number);
		account = (Account)q.getSingleResult();
		}catch(NoResultException e){
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method used to change balance of Account object by certain Integer amount with usage of EclipseLink transaction. In case of any error (any
	 * Throwable), transaction is rolledback.
	 * @param account Account object which balance has to be changed
	 * @param amount Integer number by which Account object balance has to be modified.
	 * @return boolean Returns true if operation was successful, returns false otherwise.
	 * @exception Throwable.
	 */
	
	public boolean changeBalance(Account account, int amount){
		
		try{
			
			em.getTransaction().begin();
			account.changeBalance(amount);
			em.merge(account);
			em.getTransaction().commit();
			
			return true;
		}catch(Throwable e){
			
			em.getTransaction().rollback();
		}
		
		return false;
	}
}
