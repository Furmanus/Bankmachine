package pl.furman.languages;

import java.util.HashMap;

/**
 * Abstract class which contains HashMap which keys are languages used in application. Each language is another HashMap which contains messages
 * identified by String key in different languages.
 * @author Docent Furman
 *
 */
public abstract class Languages {

	private static HashMap<String, HashMap<String, String>> messages = new HashMap<String, HashMap<String,String>>();
	
	static{
		
		HashMap<String, String> english = new HashMap<String, String>();
		HashMap<String, String> polski = new HashMap<String, String>();
		
		english.put("welcome", "Welcome! Please insert your card.");
		polski.put("welcome", "Witaj! Włóż kartę do bankomatu.");
		
		english.put("insert", "Insert card");
		polski.put("insert", "Włóż kartę");
		
		english.put("quit", "Quit");
		polski.put("quit", "Wyjście");
		
		english.put("cardNumber", "Please enter your card number.");
		polski.put("cardNumber", "Podaj numer swojej karty.");
		
		english.put("againPin", "Wrong PIN. Try again.");
		polski.put("againPin", "Zły PIN. Spróbuj ponownie.");
		
		english.put("wrongPin", "Wrong PIN entered three times");
		polski.put("wrongPin", "Zły PIN podany trzy razy");
		
		english.put("enterPin", "Please enter PIN.");
		polski.put("enterPin", "Podaj PIN.");
		
		english.put("welcomeUser", "Welcome ");
		polski.put("welcomeUser", "Witaj ");
		
		english.put("balance", "Balance: ");
		polski.put("balance", "Saldo: ");
		
		english.put("balance", "Balance: ");
		polski.put("balance", "Saldo: ");
		
		english.put("withdraw", "Withdraw money");
		polski.put("withdraw", "Wypłać gotówkę");
		
		english.put("add", "Add funds");
		polski.put("add", "Wpłać gotówkę");
		
		english.put("addAmount", "Chose amount of money to add");
		polski.put("addAmount", "Podaj kwotę do wpłaty");
		
		english.put("withdrawAmount", "Select amount to withdraw");
		polski.put("withdrawAmount", "Wybierz kwotę do wypłaty");
		
		english.put("other", "other");
		polski.put("other", "inna");
		
		english.put("customAmount", "Chose amount of money to withdraw");
		polski.put("customAmount", "Wybierz kwotę do wypłaty");
		
		english.put("invalidAmount", "Wrong input data");
		polski.put("invalidAmount", "Nieprawidłowy format danych.");
		
		english.put("wrongCard", "Error: uknown card.");
		polski.put("wrongCard", "Błąd: nieznana karta.");
		
		english.put("goodbye", "Goodbye.");
		polski.put("goodbye", "Do widzenia.");
		
		english.put("success", "Operation successful.");
		polski.put("success", "Operacja udana.");
		
		english.put("restart", "Press \"A\" to start again.");
		polski.put("restart", "Naciśnij \"A\" by zacząć ponownie.");
		
		messages.put("english", english);
		messages.put("polski", polski);
	}
	
	/**
	 * Getter for HashMap with languages and messages.
	 * @return HashMap<String, HashMap<String, String>> Returns HashMap with languages as keys. Values are another HashMaps with messages identifier
	 * as keys and messages in desired language as values.
	 */
	public static HashMap<String, HashMap<String, String>> getMessages(){
		
		return messages;
	}
}
