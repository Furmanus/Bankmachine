package pl.furman.scenes;

/**
 * Enum type of letters used in BankButton class.
 * @author Docent Furman
 *
 */
public enum ButtonSigns {
	A('a', "A"),
	B('b', "B"),
	C('c', "C"),
	D('d', "D");
	
	private final char sign;
	private final String displaySign;
	
	/**
	 * Constructor which to given enum sets two parameters: its char value and String text which is drawn on button.
	 * @param sign This parameter is passed to button event handler as char value.
	 * @param displaySign This String parameter will be drawn on button.
	 */
	ButtonSigns(char sign, String displaySign){
		
		this.sign = sign;
		this.displaySign = displaySign;
	}
	
	/**
	 * Returns char value of this enum.
	 * @return Returns char value of this enum.
	 */
	public char getSign(){
		
		return sign;
	}
	
	/**
	 * Returns String text of this enum.
	 * @return Returns String text of this enum.
	 */
	public String toString(){
		
		return displaySign;
	}
}
