package mapMaker;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mapMaker.GeneratorManager.Generator;
import mapMaker.controllers.MainWindowController;
import mapMaker.generators.MapGenerator;
import mapMaker.generators.RandomMapGenerator;
import mapMaker.map.Map;
import mapMaker.viewers.MapViewer;

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
		Pane pane = buildMainWindow();
		
		if (pane == null)
			pane = new Pane();
		
		return new Scene(pane,600, 400);
	}

	private Pane buildMainWindow() {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("view/MainWindow.fxml"));
		Pane pane;
		
		try {
			pane = loader.load();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return null;
		}
		
		MainWindowController controller = loader.getController();
		
		controller.setGenerate(() -> {
			int width = controller.getWidth();
			int height = controller.getHeight();
			Generator genType = controller.getGenerator();
			
			Map map = GeneratorManager.get(genType).generate(width, height);
			MapViewer viewer = new MapViewer();
			Canvas canvas = viewer.createView(map);
			controller.setCanvas(canvas);
		});
		
		
		return pane;
	}
	
}
