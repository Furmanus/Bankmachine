package pl.furman.exceptions;

/**
 * Exception for withdrawing money from account.
 * @author Docent Furman
 *
 */
public class WithdrawException extends Exception {

	public String getErrorMessage(){
		
		return ("Invalid amount to withdraw.");
	}
}
