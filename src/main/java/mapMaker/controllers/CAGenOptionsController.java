package mapMaker.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mapMaker.generators.CAGenOptions;

public class CAGenOptionsController implements Initializable{

	@FXML
	private TextField generationsField;
	
	@FXML
	private TextField upThreshold;

	@FXML
	private TextField downThreshold;

	@FXML
	private TextField initProbabilityThreshold;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CAGenOptions defaultOptions = new CAGenOptions();
		generationsField.setText(Integer.toString(defaultOptions.generations));
		upThreshold.setText(Byte.toString(defaultOptions.getCaOptions().upTreshold));
		downThreshold.setText(Byte.toString(defaultOptions.getCaOptions().downThreshold));
		initProbabilityThreshold.setText(Float.toString(defaultOptions.initialProbability));
	}

	public CAGenOptions getOptions() {
		CAGenOptions options = new CAGenOptions();
		
		options.generations = Integer.parseInt(generationsField.getText());
		options.initialProbability = Float.parseFloat(initProbabilityThreshold.getText());
		options.getCaOptions().upTreshold = Byte.parseByte(upThreshold.getText());
		options.getCaOptions().downThreshold = Byte.parseByte(downThreshold.getText());
		
		return options;
	}

}
