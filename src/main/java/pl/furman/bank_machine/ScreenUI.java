package pl.furman.bank_machine;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pl.furman.interfaces.ScreenInterface;
import pl.furman.scenes.MainScene;
import pl.furman.scenes.SceneScreen;

/**
 * Class used to interact with bankmachine screen. Implements ScreenInterface interface.
 * @author Łukasz Lach
 */
public class ScreenUI implements ScreenInterface {
	
	private MainScene mainScene;
	private SceneScreen screen;
	private Text message;
	
	/**
	 * Public constructor for ScreenUI class.
	 * @param mainScene MainScene object is used to get SceneScreen object, where we can draw text.
	 */
	public ScreenUI(MainScene mainScene){
		
		this.mainScene = mainScene;
		screen = mainScene.getScreen();
		screen.setAlignment(Pos.TOP_LEFT);
	}

	/**
	 * Draws text on screen in x row and y column.
	 * @param x Row where we want to draw.
	 * @param y Column where we want to draw.
	 * @param text Text to draw.
	 */
	public void drawText(int x, int y, String text) {
		
		message = new Text(text);
		message.setFont(new Font(16));
		
		screen.add(message, x, y);
	}

	/**
	 * Clears content of screen (remove all children nodes).
	 */
	public void clearScreen() {
		
		screen.getChildren().clear();
	}

}
