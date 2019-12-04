package mapMaker.controllers.caMapGenerator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.TilesISGenOptions;
import mapMaker.optionInterfaces.Options;
import mapMaker.optionInterfaces.OptionsController;
import mapMaker.validators.FloatValidator;

public class TilesISGenController implements Initializable, OptionsController{

	@FXML
	private TextField tilesXField;
	@FXML
	private TextField tilesYField;
	@FXML
	private TextField highProbabilityField;
	@FXML
	private TextField lowProbabilityField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TilesISGenOptions options = new TilesISGenOptions();
		
		tilesXField.setText(Integer.toString(options.tilesX));
		tilesYField.setText(Integer.toString(options.tilesY));
		highProbabilityField.setText(Float.toString(options.highProbability));
		lowProbabilityField.setText(Float.toString(options.lowProbability));

		FloatValidator tilesValidator = new FloatValidator(-1, 2);
		tilesValidator.add(tilesXField);
		tilesValidator.add(tilesYField);
		
		FloatValidator probabilityValidator = new FloatValidator(0, 1);
		probabilityValidator.add(highProbabilityField);
		probabilityValidator.add(lowProbabilityField);
	}

	@Override
	public Options getOptions() {
		TilesISGenOptions options = new TilesISGenOptions();
		
		options.tilesX = Integer.parseInt(tilesXField.getText());
		options.tilesY = Integer.parseInt(tilesYField.getText());
		options.highProbability = Float.parseFloat(highProbabilityField.getText());
		options.lowProbability = Float.parseFloat(lowProbabilityField.getText());
		
		return options;
	}
}
