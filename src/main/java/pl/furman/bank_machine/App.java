package pl.furman.bank_machine;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.furman.interfaces.ScreenInterface;
import pl.furman.scenes.MainScene;

public class App extends Application {
	
    public static void main( String[] args ){
    	
    	launch(args);
    	//BankMachine bank = BankMachine.getInstance();
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
