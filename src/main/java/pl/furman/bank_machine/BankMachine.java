package pl.furman.bank_machine;

import pl.furman.database.DataBaseJDBC;
import pl.furman.database.DataBaseJPA;
import pl.furman.exceptions.AddMoneyException;
import pl.furman.exceptions.WithdrawException;
import pl.furman.interfaces.DatabaseInterface;
import pl.furman.interfaces.ScreenInterface;
import pl.furman.languages.Languages;

/**
 * Main class of application. Most operations performed on bankmachine are defined here. Class implements singleton design pattern.
 * @author Łukasz Lach
 */
public class BankMachine {

	private static BankMachine instance = null;
	private Account examinedAccount = null;
	private DatabaseInterface databaseAccess = new DataBaseJPA();
	private String chosenLanguage;
	private ScreenInterface screenUI;
	private MachineStates state;
	private String examinedAccountNumber = "";
	private String examinedPinNumber = "";
	private String examinedAmountNumber = "";
	private int wrongPinCounter = 0;
	
	/**
	 * Private constructor for BankMachine instance. Instance can be get by getInstance() method. Constructor sets instance initial state as INIT and triggers takeAction method once to load language screen
	 * @param screenUI Object used to perform operations (write text and clear) on bankmachine screen
	 */
	private BankMachine(ScreenInterface screenUI){
		
		this.screenUI = screenUI;
		state = MachineStates.INIT;
		takeAction('a', null, null);
	}
	
	/**
	 * Bankmachine instance main function. Triggered everytime any button is pressed by user. Action performed by this method depends on instance current state
	 * @param sign Char representing user action by pressing one of A, B, C, D buttons. Passed from event listener of BankButton object
	 * @param number Integer representing user action performed by pressing one of numeric keys on keypad. Passed from event listener of BankNumberButton object
	 * @param type String representing user action by pressing either "return" key or "enter" key on keypad. Passed from event listener of MiscButtons object
	 */
	public void takeAction(char sign, String number, String type){
		
		switch(state){
		
			case INIT:
				
				setLanguageScreen();
				state = MachineStates.CHOOSE_LANGUAGE;
				break;
				
			case CHOOSE_LANGUAGE:
				
				if(sign == 'a' || sign == 'b'){
					
					chosenLanguage = (sign == 'a') ? "english" : "polski";
					
					String welcomeMsg = Languages.getMessages().get(chosenLanguage).get("welcome");
					
					screenUI.clearScreen();
					screenUI.drawText(2, 1, welcomeMsg);
					screenUI.drawText(1, 2, "[a] " + Languages.getMessages().get(chosenLanguage).get("insert"));
					screenUI.drawText(1, 3, "[b] " + Languages.getMessages().get(chosenLanguage).get("quit"));
					
					state = MachineStates.WELCOME_SCREEN;
				}
				break;
				
			case WELCOME_SCREEN:
				
				if(sign == 'a'  || sign == 'b'){
					
					if(sign == 'a'){
						
						setLogScreen(Languages.getMessages().get(chosenLanguage).get("cardNumber"));
						state = MachineStates.ACCOUNT_NUMBER;
					}else{
						
						setExitScreen("");
						state = MachineStates.EXIT;
					}
				}
				
				break;
				
			case ACCOUNT_NUMBER:
				
				if(number != null){
					
					if(examinedAccountNumber.length() < 8){
						
						examinedAccountNumber += number;
						screenUI.drawText(3, 3, examinedAccountNumber);
					}
				}
				
				if(examinedAccountNumber.length() > 0 && type == "return"){
					
					examinedAccountNumber = examinedAccountNumber.substring(0, examinedAccountNumber.length() - 1);
					setLogScreen(Languages.getMessages().get(chosenLanguage).get("cardNumber"));
					screenUI.drawText(3, 3, examinedAccountNumber);
				}
				
				if(type == "enter"){
										
					String accountNumber = examinedAccountNumber;
					
					String verify = verifyAccount(accountNumber);
					examinedAccountNumber = "";
					
					if(verify == "ok"){
						
						setLogScreen(Languages.getMessages().get(chosenLanguage).get("enterPin"));
						state = MachineStates.GET_PIN;
					}else{
						
						setExitScreen(verify);
						state = MachineStates.EXIT;
					}
				}
				
				break;
				
			case GET_PIN:
				
				if(number != null){
					
					if(examinedPinNumber.length() < 4){
						
						examinedPinNumber += number;
						screenUI.drawText(3, 3, examinedPinNumber);
					}
				}	
				
				if(examinedPinNumber.length() > 0 && type == "return"){
					
					examinedPinNumber = examinedPinNumber.substring(0, examinedPinNumber.length() - 1);
					setLogScreen(Languages.getMessages().get(chosenLanguage).get("enterPin"));
					screenUI.drawText(3, 3, examinedPinNumber);
				}
					
				if(type == "enter"){
					
					if(examinedPinNumber.equals(examinedAccount.getPin())){
						
						setMainScreen(examinedAccount, "");
						wrongPinCounter = 0;
						examinedPinNumber = "";
						state = MachineStates.ACCOUNT_SCREEN;
					}else{
						
						wrongPinCounter++;
						examinedPinNumber = "";
						setLogScreen(Languages.getMessages().get(chosenLanguage).get("againPin"));
						screenUI.drawText(3, 3, examinedPinNumber);
							
						if(wrongPinCounter == 3){
							
							wrongPinCounter = 0;
							examinedPinNumber = "";
							setExitScreen(Languages.getMessages().get(chosenLanguage).get("wrongPin"));
							state = MachineStates.EXIT;
						}
					}
				}
						
				break;
				
			case ACCOUNT_SCREEN:
				
				if(sign == 'a' || sign == 'b' || sign == 'c'){
					
					if(sign == 'a'){
						
						setWithdrawScreen();
						state = MachineStates.WITHDRAW;
					}else if(sign == 'b'){
						
						setLogScreen(Languages.getMessages().get(chosenLanguage).get("addAmount"));
						state = MachineStates.ADD_MONEY;
					}else{
						
						setExitScreen("");
						state = MachineStates.EXIT;
					}
				}
				
				break;
				
			case WITHDRAW:
				
				if(number == null && type == null){
					
					try{
						
						if(sign == 'a'){
								
							examinedAccount.withdrawMoney(50);
						}else if(sign == 'b'){
							
							examinedAccount.withdrawMoney(100);
						}else if(sign == 'c'){
							
							examinedAccount.withdrawMoney(200);
						}
						
						setExitScreen(Languages.getMessages().get(chosenLanguage).get("success"));
						state = MachineStates.EXIT;
					}
					catch (WithdrawException e) {
						
						setExitScreen(e.getErrorMessage());
						state = MachineStates.EXIT;
					}
					
					if(sign == 'd'){
						
						setLogScreen(Languages.getMessages().get(chosenLanguage).get("customAmount"));
						state = MachineStates.CUSTOM_WITHDRAW;
					}
				}
				
				break;
				
			case CUSTOM_WITHDRAW:
				
				if(number != null){
					
					if(examinedAmountNumber.length() < 3){
						
						examinedAmountNumber += number;
						screenUI.drawText(3, 3, examinedAmountNumber);
					}
				}
				
				if(examinedAmountNumber.length() > 0 && type == "return"){
					
					examinedAmountNumber = examinedAmountNumber.substring(0, examinedAmountNumber.length() - 1);
					setLogScreen(Languages.getMessages().get(chosenLanguage).get("customAmount"));
					screenUI.drawText(3, 3, examinedAmountNumber);
				}
				
				if(type == "enter"){
					
					try {
						
						examinedAccount.withdrawMoney(getNumberFromString(examinedAmountNumber));
						setExitScreen(Languages.getMessages().get(chosenLanguage).get("success"));
						examinedAmountNumber = "";
						state = MachineStates.EXIT;
					} catch (WithdrawException e) {
						
						setExitScreen(e.getErrorMessage());
						examinedAmountNumber = "";
						state = MachineStates.EXIT;
					}
				}
				
				break;
				
			case ADD_MONEY:
				
				if(number != null){
					
					if(examinedAmountNumber.length() < 3){
						
						examinedAmountNumber += number;
						screenUI.drawText(3, 3, examinedAmountNumber);
					}
				}
				
				if(examinedAmountNumber.length() > 0 && type == "return"){
					
					examinedAmountNumber = examinedAmountNumber.substring(0, examinedAmountNumber.length() - 1);
					setLogScreen(Languages.getMessages().get(chosenLanguage).get("addAmount"));
					screenUI.drawText(3, 3, examinedAmountNumber);
				}
				
				if(type == "enter"){
					
					try {
							
						examinedAccount.addFunds(getNumberFromString(examinedAmountNumber));
						setExitScreen(Languages.getMessages().get(chosenLanguage).get("success"));
						examinedAmountNumber = "";
						state = MachineStates.EXIT;
					} catch (AddMoneyException e) {
						
						setExitScreen(e.getErrorMessage());
						examinedAmountNumber = "";
						state = MachineStates.EXIT;
					}
				}
								
				break;
				
			case EXIT:
				
				if(sign == 'a'){
					
					screenUI.clearScreen();
					screenUI.drawText(2, 1, "Choose a language/Wybierz język:");
					screenUI.drawText(1, 3, "[a]: English");
					screenUI.drawText(1, 4, "[b]: Polski");
					state = MachineStates.CHOOSE_LANGUAGE;
				}
				
				break;
		}
	}
	
	/**
	 * Static method used to get access to BankMachine singleton instance
	 * @param screenUI Object used to write on or clear bankmachine screen
	 * @return BankMachine Returns instance of BankMachine singleton class. Creates new BankMachine in case it wasn't created already.
	 */
	public static BankMachine getInstance(ScreenInterface screenUI){
		
		if(instance == null){
			
			instance = new BankMachine(screenUI);
		}
		
		return instance;
	}
	
	/**
	 * Sets clear screen with single line of text
	 * @param text Text that has to be drawn on screen
	 */
	private void setLogScreen(String text){
			
		screenUI.clearScreen();
		screenUI.drawText(2, 1, text);
	}
	
	/**
	 * Method used to verify account number entered by user.
	 * @param number String account number entered by user.
	 * @return Returns String which is either "ok" (account with given number exists in database) or returns error message.
	 */
	private String verifyAccount(String number){
		
		if(databaseAccess.verifyAccountNumber(number) == true){
			
			this.examinedAccount = databaseAccess.getAccountByNumber(number);
			
			return "ok";
		}
		
		return Languages.getMessages().get(chosenLanguage).get("wrongCard");
	}
	
	/**
	 * Sets exit screen after successful operation
	 * @param text Text which has to be drawn on screen
	 */
	private void setExitScreen(String text){
		
		screenUI.clearScreen();
		screenUI.drawText(3, 1, text);
		screenUI.drawText(2, 2, Languages.getMessages().get(chosenLanguage).get("goodbye"));
		screenUI.drawText(2, 3, Languages.getMessages().get(chosenLanguage).get("restart"));
	}
	
	/**
	 * Sets main bankmachine screen after user is successfully logged in.
	 * @param account Examined Account object.
	 * @param optionalText Optional text drawn on screen.
	 */
	private void setMainScreen(Account account, String optionalText){
		
		screenUI.clearScreen();
		screenUI.drawText(2, 1, Languages.getMessages().get(chosenLanguage).get("welcomeUser") + account.getOwner());
		screenUI.drawText(2, 2, Languages.getMessages().get(chosenLanguage).get("balance") + account.getBalance());
		screenUI.drawText(2, 3, optionalText);
		screenUI.drawText(1, 4, "[a] " + Languages.getMessages().get(chosenLanguage).get("withdraw"));
		screenUI.drawText(1, 5, "[b] " + Languages.getMessages().get(chosenLanguage).get("add"));
		screenUI.drawText(1, 6, "[c] " + Languages.getMessages().get(chosenLanguage).get("quit"));
	}
	
	/**
	 * Sets screen where user can chose amount of money to withdraw from account.
	 */
	private void setWithdrawScreen(){
		
		screenUI.clearScreen();
		screenUI.drawText(8, 1, Languages.getMessages().get(chosenLanguage).get("withdrawAmount"));
		screenUI.drawText(2, 2, "[a] 50 PLN");
		screenUI.drawText(2, 3, "[b] 100 PLN");
		screenUI.drawText(2, 4, "[c] 200 PLN");
		screenUI.drawText(2, 5, "[d] " + Languages.getMessages().get(chosenLanguage).get("other"));
	}
	
	/**
	 * Sets screen where use can chose prefered language.
	 */
	private void setLanguageScreen(){
		
		screenUI.clearScreen();
		screenUI.drawText(2, 1, "Choose a language/Wybierz język:");
		screenUI.drawText(1, 3, "[a]: English");
		screenUI.drawText(1, 4, "[b]: Polski");
	}
	
	/**
	 * Method used to convert String number (like "12434") into integer
	 * @param text String to be converted into integer number
	 * @return int Returns converted Integer number
	 */
	private int getNumberFromString(String text){
		
		int result = 0;
		
		for(int i=text.length() - 1, j=0; i >= 0 ; i--, j++){
			
			result += (Character.getNumericValue(text.charAt(i))) * Math.pow(10, j);
		}
		
		return result;
	}
}
