package mapMaker.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import lombok.Setter;

public class MainWindowController implements Initializable{

	@FXML
	private Button generateButton;

	@FXML
	private ScrollPane mapPane;
	
	@Setter
	private Runnable generate;
	
//	private Canvas canvas;
	
	public void setCanvas(Canvas canvas) {
//		this.canvas = canvas;
		mapPane.setContent(canvas);
	}
	
	private void generate() {
		if(generate != null)
			generate.run();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		generateButton.setOnAction(e -> generate());
	}

}
