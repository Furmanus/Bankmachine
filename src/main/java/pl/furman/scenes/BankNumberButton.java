package pl.furman.scenes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import pl.furman.bank_machine.BankMachine;

/**
 * Class of bankmachine numeric keypad buttons.
 * @author Łukasz Lach
 *
 */
public class BankNumberButton extends Button {

	private int value;
	private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		
		public void handle(MouseEvent e) { 
			   
			BankMachine bank = BankMachine.getInstance(null);
			bank.takeAction('e', new Integer(value).toString(), null);  
		} 
	}; 
	
	/**
	 * Public constructor of BankButton. Takes Enum ButtonSigns as argument which String is drawn on button. Constructor sets font, size and
	 * adds event handler. Also sets "choiceButton" style class for this object. Event handler takes bank machine instance and triggers its
	 * takeAction() method with second parameter equal to button String representation of button's value. 
	 * @param value This parameter is an integer value representing text of button and its value passed as String by its event handler.
	 */
	public BankNumberButton(int value){
		
		super();
		this.value = value;
		this.setText(new Integer(value).toString());
		this.setPrefHeight(40);
		this.setPrefWidth(40);
		this.setFont(new Font(16));
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
}
