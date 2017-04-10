package pl.furman.scenes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import pl.furman.bank_machine.BankMachine;

public class BankNumberButton extends Button {

	private int value;
	private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		
		public void handle(MouseEvent e) { 
			   
			BankMachine bank = BankMachine.getInstance(null);
			bank.takeAction('e', new Integer(value).toString(), null);  
		} 
	}; 
	
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
