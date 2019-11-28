package mapMaker.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.Setter;
import mapMaker.GeneratorManager.Generator;
import mapMaker.view.View;

public class MainWindowController implements Initializable{

	private Generator selectedGenerator = Generator.Random;
	
	@FXML
	private Button generateButton;

	@FXML
	private StackPane mapPane;

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
		
		Platform.runLater(() -> {
			mapPane.getChildren().clear();
			mapPane.getChildren().add(canvas);
		});
	}
	
	private void generate() {
		generateButton.setDisable(true);
		
		new Thread(() -> {
			if(generate != null)
				generate.run();
			
			Platform.runLater(() -> generateButton.setDisable(false));
		}).start();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		generateButton.setOnAction(e -> generate());
		buildSelectGeneratorView();
	}

	private void buildSelectGeneratorView() {
		RadioButton radio;
		HBox hbox;
		Button optionButton;
		ToggleGroup group = new ToggleGroup();
		generatorsBox.getChildren().clear();
		
		for(Generator g : Generator.values()) {
			hbox = new HBox();
			
			radio = new RadioButton(g.toString());
			radio.setToggleGroup(group);
			radio.setOnAction(e -> selectedGenerator = g);
			hbox.getChildren().add(radio);
			
			if(g.getOptionsFXML() != null) {
				optionButton = new Button("*");//("Options");
				optionButton.setOnAction(e -> {
					View.openGeneratorOptions(g);
				});
				hbox.getChildren().add(optionButton);
			}

			generatorsBox.getChildren().add(hbox);
		}
		
		if(group.getToggles().size() > 0)
			group.getToggles().get(0).setSelected(true);
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
