package pl.furman.bank_machine;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pl.furman.interfaces.ScreenInterface;
import pl.furman.scenes.MainScene;
import pl.furman.scenes.SceneScreen;

public class ScreenUI implements ScreenInterface {
	
	private MainScene mainScene;
	private SceneScreen screen;
	private Text message;
	
	public ScreenUI(MainScene mainScene){
		
		this.mainScene = mainScene;
		screen = mainScene.getScreen();
		screen.setAlignment(Pos.TOP_LEFT);
	}

	public void drawText(int x, int y, String text) {
		
		message = new Text(text);
		message.setFont(new Font(16));
		
		screen.add(message, x, y);
	}

	public void clearScreen() {
		
		screen.getChildren().clear();
	}

}
