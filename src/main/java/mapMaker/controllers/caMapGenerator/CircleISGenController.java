package mapMaker.controllers.caMapGenerator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.CircleISGenOptions;
import mapMaker.optionInterfaces.Options;
import mapMaker.optionInterfaces.OptionsController;
import mapMaker.validators.FloatValidator;
import mapMaker.validators.IntegerValidator;

public class CircleISGenController implements Initializable, OptionsController{

	@FXML
	private TextField centreXField;
	@FXML
	private TextField centreYField;
	@FXML
	private TextField centreRField;
	@FXML
	private TextField circleRField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CircleISGenOptions options = new CircleISGenOptions();
		
		centreXField.setText(Float.toString(options.centreX));
		centreYField.setText(Float.toString(options.centreY));
		centreRField.setText(Integer.toString(options.centreR));
		circleRField.setText(Integer.toString(options.circleR));
		
		FloatValidator centreValidator = new FloatValidator(-1, 2);
		centreValidator.add(centreXField);
		centreValidator.add(centreYField);
		
		IntegerValidator sizeValidator =new IntegerValidator(0, 1000000);
		sizeValidator.add(centreRField);
		sizeValidator.add(circleRField);
	}

	@Override
	public Options getOptions() {
		CircleISGenOptions options = new CircleISGenOptions();
		
		options.centreX = Float.parseFloat(centreXField.getText());
		options.centreY = Float.parseFloat(centreYField.getText());
		options.centreR = Integer.parseInt(centreRField.getText());
		options.circleR = Integer.parseInt(circleRField.getText());
		
		return options;
	}
}
