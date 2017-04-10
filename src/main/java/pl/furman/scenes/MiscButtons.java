package pl.furman.scenes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import pl.furman.bank_machine.BankMachine;

public class MiscButtons extends Button {
	
	private String id;
	private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		
		public void handle(MouseEvent e) { 
			   
			BankMachine bank = BankMachine.getInstance(null);
			bank.takeAction('e', null, id); 
		} 
	}; 

	public MiscButtons(String type){
		
		this.id = type;
		this.setPrefHeight(40);
		this.setPrefWidth(40);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		this.setId(type);
	}
}
