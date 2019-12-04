package mapMaker.controllers.caMapGenerator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.EvenlyISGenOptions;
import mapMaker.optionInterfaces.Options;
import mapMaker.optionInterfaces.OptionsController;
import mapMaker.validators.FloatValidator;

public class EvenlyISGenController implements Initializable, OptionsController{
	
	@FXML
	private TextField probabilityField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EvenlyISGenOptions options = new EvenlyISGenOptions();
		
		probabilityField.setText(Float.toString(options.probability));
		
		FloatValidator probabilityValidator = new FloatValidator(0, 1);
		probabilityValidator.add(probabilityField);
	}

	@Override
	public Options getOptions() {
		EvenlyISGenOptions options = new EvenlyISGenOptions();
		
		options.probability = Float.parseFloat(probabilityField.getText());
		
		return options;
	}
}
