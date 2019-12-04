package mapMaker.controllers.caMapGenerator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.GradientISGenOptions;
import mapMaker.optionInterfaces.Options;
import mapMaker.optionInterfaces.OptionsController;
import mapMaker.validators.FloatValidator;

public class GradientISGenController implements Initializable, OptionsController{

	@FXML
	private TextField startProbabilityField;
	@FXML
	private TextField endProbabilityField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GradientISGenOptions options = new GradientISGenOptions();
		
		startProbabilityField.setText(Float.toString(options.startProbability));
		endProbabilityField.setText(Float.toString(options.endProbability));
		
		FloatValidator probabilityValidator = new FloatValidator(0, 1);
		probabilityValidator.add(startProbabilityField);
		probabilityValidator.add(endProbabilityField);
	}

	@Override
	public Options getOptions() {
		GradientISGenOptions options = new GradientISGenOptions();

		options.startProbability = Float.parseFloat(startProbabilityField.getText());
		options.endProbability = Float.parseFloat(endProbabilityField.getText());
		
		return options;
	}
}
