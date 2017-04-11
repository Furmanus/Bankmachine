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

/**
 * MainScene class instance is a Scene containing every other components from bank machine GUI. Its root node is a border pane, which contains
 * children nodes like VBoxes, Keypad and SceneScreen
 * @author Docent Furman
 *
 */
public class MainScene extends Scene {
	
	private static BorderPane borderPane = new BorderPane();
	private SceneScreen screen = new SceneScreen();
	private Keypad keypad = new Keypad();

	/**
	 * Public constructor. Firstly it triggers upper class Scene constructor with borderpane and size as parameters. At next step is sets up
	 * A, B, C, D BankButton buttons in two VBox nodes of left and right side of screen. Finally it sets up SceneScreen object in center of border
	 * pane and Keypad object at bottom
	 */
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
	
	/**
	 * Getter for SceneScreen screen object.
	 * @return Returns SceneScreen screen object.
	 */
	public SceneScreen getScreen(){
		
		return screen;
	}
	
	/**
	 * Getter for Keypad keypa object.
	 * @return Returns Keypad keypad object.
	 */
	public Keypad getKeypad(){
		
		return keypad;
	}
}
