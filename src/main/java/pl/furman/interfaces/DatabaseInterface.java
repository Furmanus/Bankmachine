package pl.furman.interfaces;

import pl.furman.bank_machine.Account;

/**
 * Interface to database connection.
 * @author Docent Furman
 *
 */
public interface DatabaseInterface {

	public Account getAccountByNumber(String number);
	
	public boolean verifyAccountNumber(String number);
	
	public boolean changeBalance(Account account, int amount);
}
