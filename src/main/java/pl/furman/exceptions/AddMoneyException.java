package pl.furman.exceptions;

public class AddMoneyException extends Exception {

	public String getErrorMessage(){
		
		return ("You don't have that much money, sorry.");
	}
}
