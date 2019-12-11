package mapMaker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import mapMaker.GeneratorManager.Generator;
import mapMaker.controllers.MainWindowController;
import mapMaker.generators.MapGenerator;
import mapMaker.map.Map;
import mapMaker.view.InitialStateViewManager;
import mapMaker.view.View;
import mapMaker.viewers.MapViewer;

public class App extends Application{
	
	public static void main(String[] args) {
		System.out.println("Map Maker");		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		View.load();
		InitialStateViewManager.load();
		stage.setTitle("MapMaker");
		stage.setScene(createScene());
		stage.setOnCloseRequest(h -> Platform.exit());
		
		stage.show();
	}

	private Scene createScene() {
		Pane pane = buildMainWindow();
		
		if (pane == null)
			pane = new Pane();
		
		return new Scene(pane, 600, 400, true, SceneAntialiasing.DISABLED);
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
		
		controller.setGenerate((progressListiner) -> {
			int width = controller.getWidth();
			int height = controller.getHeight();
			Generator genType = controller.getGenerator();
			
			MapGenerator mapGenerator = GeneratorManager.get(genType);
			mapGenerator.setProgressListiner(progressListiner);
			Map map = mapGenerator.generate(width, height);
			MapViewer viewer = new MapViewer();
			Canvas canvas = viewer.createView(map);
			controller.setCanvas(canvas);
			

		});
		
		controller.setSaveCanvas(canvas -> {
			Platform.runLater(() -> {
				FileChooser chooser = new FileChooser();
				
				chooser.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
				File file  = chooser.showSaveDialog(null);
				
				WritableImage wi = new WritableImage((int)canvas.getWidth(),(int)canvas.getHeight());
				canvas.snapshot(null, wi);
				BufferedImage im = SwingFXUtils.fromFXImage(wi, null);
				try {
					ImageIO.write(im, "png", file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});			
		});
		
		
		return pane;
	}
	
}
