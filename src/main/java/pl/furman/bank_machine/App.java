package pl.furman.bank_machine;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.furman.interfaces.ScreenInterface;
import pl.furman.scenes.MainScene;

/**
 * Init class responsible for launching application and initializing {@code Stage} and {@code Scene} mainScene object.
 * @author Łukasz Lach
 *
 */
public class App extends Application {
	
    public static void main( String[] args ){
    	
    	launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Bankmachine by Łukasz Lach");
		MainScene mainScene = new MainScene();
		mainScene.getStylesheets().add("./pl/furman/styles/styles.css");
		ScreenInterface screenUI = new ScreenUI(mainScene);
		BankMachine bank = BankMachine.getInstance(screenUI);
		stage.setScene(mainScene);
		stage.setResizable(false);
		stage.show();
	}
}
