package pl.furman.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Keypad class avaible to user at the bottom of bankmachine. Contains numeric BankNumberButtons and MiscButtons return and enter.
 * @author Docent Furman
 *
 */
public class Keypad extends GridPane {
	
	/**
	 * Public constructor. Creates appropriate buttons and places them in correct places on grid.
	 */
	public Keypad(){
		
		super();
		
		this.setAlignment(Pos.BOTTOM_CENTER);

		this.add(new BankNumberButton(1), 1, 2);
		this.add(new BankNumberButton(2), 2, 2);
		this.add(new BankNumberButton(3), 3, 2);
		this.add(new BankNumberButton(4), 1, 3);
		this.add(new BankNumberButton(5), 2, 3);
		this.add(new BankNumberButton(6), 3, 3);
		this.add(new BankNumberButton(7), 1, 4);
		this.add(new BankNumberButton(8), 2, 4);
		this.add(new BankNumberButton(9), 3, 4);
		this.add(new BankNumberButton(0), 2, 5);
		this.add(new MiscButtons("enter"), 3, 5);
		this.add(new MiscButtons("return"), 1, 5);
	}
}
