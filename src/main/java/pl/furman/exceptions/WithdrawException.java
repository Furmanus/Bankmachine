package pl.furman.exceptions;

public class WithdrawException extends Exception {

	public String getErrorMessage(){
		
		return ("Invalid amount to withdraw.");
	}
}
