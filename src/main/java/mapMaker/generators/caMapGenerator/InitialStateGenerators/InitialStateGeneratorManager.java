package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import lombok.Getter;
import lombok.NonNull;
import mapMaker.optionInterfaces.Options;
import mapMaker.view.InitialStateViewManager;

public class InitialStateGeneratorManager {

	public enum InitialStateGeneratorType{
		Evenly("EvenlyISGenOptions.fxml"),
		Gradient("GradientISGenOptions.fxml"),
		Circle("CircleISGenOptions.fxml"),
		Tiles("TilesISGenOptions.fxml");
		
		@Getter
		private String optionsFXML;
		
		private InitialStateGeneratorType() {
		}
		 
		private InitialStateGeneratorType(String fxmlFilePath) {
			optionsFXML = fxmlFilePath;
		}
	}
	
	public @NonNull InitialStateGenerator get(InitialStateGeneratorType type) {
		InitialStateGenerator generator = getGeneratorWithoutOptions(type);

		Options options = InitialStateViewManager.getGeneratorOptions(type);	
		generator.setOptions(options);
		
		return generator;
	}
	
	private InitialStateGenerator getGeneratorWithoutOptions(InitialStateGeneratorType type) {
		switch (type) {
		case Evenly:
			return new EvenlyInitialStateGenerator();
		case Gradient:
			return new GradientInitialStateGenerator();
		case Circle:
			return new CircleInitialStateGenerator();
		case Tiles:
			return new TilesInitialStateGenerator();

		default:
			return new EvenlyInitialStateGenerator();
		}		
	}
}
