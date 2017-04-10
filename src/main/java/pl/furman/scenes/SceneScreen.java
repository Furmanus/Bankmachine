package pl.furman.scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SceneScreen extends GridPane {

	public SceneScreen(){
		
		super();
		
		this.getStyleClass().add("screen");
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(600);
		this.setPrefHeight(300);
	}
}
