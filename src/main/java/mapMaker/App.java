package mapMaker;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application{
	
	public static void main(String[] args) {
		System.out.println("Map Maker");
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("MapMaker");
		stage.setScene(createScene());
		stage.setOnCloseRequest(h -> Platform.exit());
		
		stage.show();
	}

	private Scene createScene() {
		Pane pane = loadMainWindow();
		
		if (pane == null)
			pane = new Pane();
		
		return new Scene(pane,600, 400);
	}

	private Pane loadMainWindow() {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("view/MainWindow.fxml"));
		
		Pane pane;
		
		try {
			pane = loader.load();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
		return pane;
	}
}
