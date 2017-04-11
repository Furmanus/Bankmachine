package pl.furman.exceptions;

/**
 * Exception for adding money to account.
 * @author Docent Furman
 *
 */
public class AddMoneyException extends Exception {

	public String getErrorMessage(){
		
		return ("You don't have that much money, sorry.");
	}
}
