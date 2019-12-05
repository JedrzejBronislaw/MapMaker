package mapMaker.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import mapMaker.CA.CAOptions.Neighborhood;
import mapMaker.generators.CAGenOptions;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.InitialStateGeneratorManager.InitialStateGeneratorType;
import mapMaker.optionInterfaces.OptionsController;
import mapMaker.validators.IntegerValidator;
import mapMaker.view.InitialStateViewManager;

public class CAGenOptionsController implements Initializable, OptionsController{

	@FXML
	private TextField generationsField;
	
	@FXML
	private TextField upThreshold;

	@FXML
	private TextField downThreshold;
	
	@FXML
	private VBox generatorsBox;

	@FXML
	private Pane isOptionPane;
	
	@FXML
	private RadioButton mooreNeighborhood;
	
	@FXML
	private RadioButton vonNeumannNeighborhood;
	
	@FXML
	private CheckBox inSitu;
	
	@Getter
	private InitialStateGeneratorType selectedGenerator = InitialStateGeneratorType.Evenly;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CAGenOptions defaultOptions = new CAGenOptions();
		generationsField.setText(Integer.toString(defaultOptions.generations));
		upThreshold.setText(Byte.toString(defaultOptions.getCaOptions().upTreshold));
		downThreshold.setText(Byte.toString(defaultOptions.getCaOptions().downThreshold));
		if(defaultOptions.getCaOptions().neighborhood == Neighborhood.Moore)
			mooreNeighborhood.setSelected(true);
		if(defaultOptions.getCaOptions().neighborhood == Neighborhood.vonNeumann)
			vonNeumannNeighborhood.setSelected(true);
		inSitu.setSelected(defaultOptions.getCaOptions().inSitu);
		
		buildSelectGeneratorView();
		
		IntegerValidator thresholdsValidator = new IntegerValidator(0, 8);
		thresholdsValidator.add(upThreshold);
		thresholdsValidator.add(downThreshold);

		IntegerValidator generationsValidator = new IntegerValidator(0, 1000000);
		generationsValidator.add(generationsField);
	}
	
	private void buildSelectGeneratorView() {
		RadioButton radio;
		HBox hbox;
		ToggleGroup group = new ToggleGroup();
		generatorsBox.getChildren().clear();
		
		for(InitialStateGeneratorType g : InitialStateGeneratorType.values()) {
			hbox = new HBox();
			
			radio = new RadioButton(g.toString());
			radio.setToggleGroup(group);
			radio.setOnAction(e -> {
				selectedGenerator = g;
				isOptionPane.getChildren().clear();
				if(g.getOptionsFXML() != null)
					isOptionPane.getChildren().add(InitialStateViewManager.getGeneratorView(g));
			});
			hbox.getChildren().add(radio);

			generatorsBox.getChildren().add(hbox);
		}
		
		if(group.getToggles().size() > 0)
			group.getToggles().get(0).setSelected(true);
	}

	public CAGenOptions getOptions() {
		CAGenOptions options = new CAGenOptions();
		
		options.generations = Integer.parseInt(generationsField.getText());
		options.getCaOptions().upTreshold = Byte.parseByte(upThreshold.getText());
		options.getCaOptions().downThreshold = Byte.parseByte(downThreshold.getText());
		options.initialStateGeneratorType = selectedGenerator;
		if(mooreNeighborhood.isSelected())
			options.getCaOptions().neighborhood = Neighborhood.Moore;
		else if(vonNeumannNeighborhood.isSelected())
			options.getCaOptions().neighborhood = Neighborhood.vonNeumann;		
		options.getCaOptions().inSitu = inSitu.isSelected();
		
		
		return options;
	}

}
