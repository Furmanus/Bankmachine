package pl.furman.scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Object of this class is a bank machine screen where all information are drawn. Screen itself is a simple GridPane object.
 * @author Łukasz Lach
 *
 */
public class SceneScreen extends GridPane {

	/**
	 * Public constructor which adds "screen" css class to this instance, sets Pos.CENTER aligment and sets preferred width and height.
	 */
	public SceneScreen(){
		
		super();
		
		this.getStyleClass().add("screen");
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(600);
		this.setPrefHeight(300);
	}
}
