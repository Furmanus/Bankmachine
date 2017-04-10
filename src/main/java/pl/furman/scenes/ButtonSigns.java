package pl.furman.scenes;

public enum ButtonSigns {
	A('a', "A"),
	B('b', "B"),
	C('c', "C"),
	D('d', "D");
	
	private final char sign;
	private final String displaySign;
	
	ButtonSigns(char sign, String displaySign){
		
		this.sign = sign;
		this.displaySign = displaySign;
	}
	
	public char getSign(){
		
		return sign;
	}
	
	public String toString(){
		
		return displaySign;
	}
}
