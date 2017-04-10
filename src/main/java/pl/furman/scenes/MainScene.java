package pl.furman.scenes;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MainScene extends Scene {
	
	private static BorderPane borderPane = new BorderPane();
	private SceneScreen screen = new SceneScreen();
	private Keypad keypad = new Keypad();

	public MainScene() {
		
		super(borderPane, 800, 600);
		
		screen.setId("screen");
		keypad.setId("keypad");
		
		VBox leftButtons = new VBox();
		VBox rightButtons = new VBox();
		
		leftButtons.setAlignment(Pos.CENTER_LEFT);
		leftButtons.setSpacing(200);
		rightButtons.setAlignment(Pos.CENTER_RIGHT);
		rightButtons.setSpacing(200);
		
		leftButtons.getChildren().add(new BankButton(ButtonSigns.A));
		leftButtons.getChildren().add(new BankButton(ButtonSigns.B));
		
		rightButtons.getChildren().add(new BankButton(ButtonSigns.C));
		rightButtons.getChildren().add(new BankButton(ButtonSigns.D));
		
		borderPane.setBottom(keypad);
		borderPane.setCenter(screen);
		borderPane.setLeft(leftButtons);
		borderPane.setRight(rightButtons);
	}
	
	public SceneScreen getScreen(){
		
		return screen;
	}
	
	public Keypad getKeypad(){
		
		return keypad;
	}
}
