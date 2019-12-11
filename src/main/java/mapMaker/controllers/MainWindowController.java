package mapMaker.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.Setter;
import mapMaker.GeneratorManager.Generator;
import mapMaker.validators.IntegerValidator;
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
	
	@FXML
	private ProgressBar progressbar;
	
	@FXML
	private MenuItem saveImageMenuItem;

	@Setter
	private Consumer<Consumer<Float>> generate;
	@Setter
	private Consumer<Canvas> saveCanvas;
	
	private Canvas canvas;
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
		
		Platform.runLater(() -> {
			mapPane.getChildren().clear();
			mapPane.getChildren().add(canvas);
		});
	}
	
	private void updateProgressBar(float percentage) {
		progressbar.setProgress(percentage);
	}
	
	private void generate() {
		generateButton.setDisable(true);
		progressbar.setVisible(true);	
		progressbar.setManaged(true);	
		
		new Thread(() -> {
			if(generate != null)
				generate.accept(percentage -> updateProgressBar(percentage));
			
			Platform.runLater(() -> {
				generateButton.setDisable(false);
				progressbar.setVisible(false);	
				progressbar.setManaged(false);	
			});
		}).start();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		progressbar.setVisible(false);	
		progressbar.setManaged(false);	
		generateButton.setOnAction(e -> generate());
		buildSelectGeneratorView();
		
		IntegerValidator intValidator = new IntegerValidator(10, 1000000);
		intValidator.add(heightField);
		intValidator.add(widthField);
		
		saveImageMenuItem.setOnAction(e -> {
			if(canvas != null && saveCanvas != null)
				saveCanvas.accept(canvas);
		});
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
