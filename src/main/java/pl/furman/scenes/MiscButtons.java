package pl.furman.scenes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import pl.furman.bank_machine.BankMachine;

/**
 * Class which object can be button of two types: either "return" (which cancels user input on screen) and "enter" (which confirms user input).
 * @author Łukasz Lach
 *
 */
public class MiscButtons extends Button {
	
	private String id;
	private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		
		public void handle(MouseEvent e) { 
			   
			BankMachine bank = BankMachine.getInstance(null);
			bank.takeAction('e', null, id); 
		} 
	}; 

	/**
	 * Public constructor for MiscButtons class. Sets id of this button, its preferred width and height and also adds event handler.
	 * Event handler takes bank machine instance and triggers its takeAction method with third parameter equal to button id.
	 * @param type
	 */
	public MiscButtons(String type){
		
		this.id = type;
		this.setPrefHeight(40);
		this.setPrefWidth(40);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		this.setId(type);
	}
}
