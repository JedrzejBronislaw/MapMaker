package mapMaker.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import lombok.Setter;
import mapMaker.GeneratorManager.Generator;

public class MainWindowController implements Initializable{

	private Generator selectedGenerator = Generator.Random;
	
	@FXML
	private Button generateButton;

	@FXML
	private ScrollPane mapPane;

	@FXML
	private TextField widthField;
	@FXML
	private TextField heightField;
	@FXML
	private VBox generatorsBox;
	
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
		buildSelectGeneratorView();
	}

	private void buildSelectGeneratorView() {
		RadioButton radio;
		ToggleGroup group = new ToggleGroup();
		generatorsBox.getChildren().clear();
		
		for(Generator g : Generator.values()) {
			radio = new RadioButton(g.toString());
			radio.setToggleGroup(group);
			generatorsBox.getChildren().add(radio);
			radio.setOnAction(e -> selectedGenerator = g);
		}
	}

	public Generator getGenerator() {
		return selectedGenerator;
	}
	
	public int getWidth() {
		int w;
		
		try {
			w = Integer.parseInt(widthField.getText());
		} catch (NumberFormatException e) {
			w = 100;
		}
		
		return w;
	}

	public int getHeight() {
		int h;
		
		try {
			h = Integer.parseInt(heightField.getText());
		} catch (NumberFormatException e) {
			h = 100;
		}
		
		return h;
	}

}
