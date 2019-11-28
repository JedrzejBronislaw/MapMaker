package mapMaker.view;

import java.io.IOException;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mapMaker.tools.MyFXMLLoader;
import mapMaker.tools.MyFXMLLoader.NodeAndController;

public class Window {

	private Stage stage;
	
	private NodeAndController nac;
	
	public Window(String fxmlPath) throws IOException, IllegalArgumentException {
		nac = new MyFXMLLoader().create(fxmlPath);
		
		
		stage = new Stage();
		
		Pane pane;
		
		if (nac.getNode() instanceof Pane)
			pane = (Pane) nac.getNode();
		else
			throw new IllegalArgumentException("root of this fxml file is not Pane");
		
		Scene scene = new Scene(pane);//, width, height);
		
		stage.setScene(scene);
	}

	public void setTitle(String title) {
		stage.setTitle(title);
	}
	
	public Initializable getController() {
		return nac.getController();
	}
	
	
	public void show() {
		stage.show();
	}
}
