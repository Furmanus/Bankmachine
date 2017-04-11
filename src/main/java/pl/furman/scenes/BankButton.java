package pl.furman.scenes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import pl.furman.bank_machine.BankMachine;

/**
 * Class of bankmachine A, B, C, D buttons.
 * @author Docent Furman
 *
 */
public class BankButton extends Button {
	
	private ButtonSigns sign;
	private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		
		public void handle(MouseEvent e) { 
			   
			BankMachine bank = BankMachine.getInstance(null);
			bank.takeAction(sign.getSign(), null, null); 
		} 
	}; 
	
	/**
	 * Public constructor of BankButton. Takes Enum ButtonSigns as argument which String is drawn on button. Constructor sets font, size and
	 * adds event handler. Also sets "choiceButton" style class for this object. Event handler takes bank machine instance and triggers its takeAction()
	 * method with first parameter equals to char received from ButtonSigns sign enum.
	 * @param sign This parameter is ButtonSigns enum representing char text of a button.
	 */
	public BankButton(ButtonSigns sign){
		
		this.sign = sign;
		this.setText(sign.toString());
		this.setPrefHeight(32);
		this.setPrefWidth(64);
		this.setFont(new Font(16));
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		this.getStyleClass().add("choiceButton");
	}
}
