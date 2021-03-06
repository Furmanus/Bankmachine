package pl.furman.bank_machine;

/**
 * Enum object which contains all possible states of bankmachine
 * @author Łukasz Lach
 */
public enum MachineStates {

	INIT, CHOOSE_LANGUAGE, WELCOME_SCREEN, ACCOUNT_NUMBER, EXIT, GET_PIN, ACCOUNT_SCREEN, WITHDRAW, ADD_MONEY, CUSTOM_WITHDRAW;
}
