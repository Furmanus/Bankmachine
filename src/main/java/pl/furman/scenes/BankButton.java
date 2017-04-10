package pl.furman.scenes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import pl.furman.bank_machine.BankMachine;

public class BankButton extends Button {
	
	private ButtonSigns sign;
	private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		
		public void handle(MouseEvent e) { 
			   
			BankMachine bank = BankMachine.getInstance(null);
			bank.takeAction(sign.getSign(), null, null); 
		} 
	}; 
	
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
