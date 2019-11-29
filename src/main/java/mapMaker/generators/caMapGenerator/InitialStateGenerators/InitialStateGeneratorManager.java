package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import lombok.Getter;
import lombok.NonNull;

public class InitialStateGeneratorManager {

	public enum InitialStateGeneratorType{
		Evenly("EvenlyISGenOptions.fxml"),
		Gradient,
		Circle,
		Tiles;
		
		@Getter
		private String optionsFXML;
		
		private InitialStateGeneratorType() {
		}
		 
		private InitialStateGeneratorType(String fxmlFilePath) {
			optionsFXML = fxmlFilePath;
		}
	}
	
	public @NonNull InitialStateGenerator get(InitialStateGeneratorType initialStateGeneratorType) {
		switch (initialStateGeneratorType) {
		case Evenly:
			return newEvenlyGen();
//		case Gradient:
//			return newGradientGen();
//		case Circle:
//			return newCircleGen();
//		case Tiles:
//			return newTilesGen();

		default:
			return newEvenlyGen();
		}
	}

	private InitialStateGenerator newEvenlyGen() {
		return new EvenlyInitialStateGenerator();
	}

	private InitialStateGenerator newGradientGen() {
		// TODO Auto-generated method stub
		return null;
	}

	private InitialStateGenerator newCircleGen() {
		// TODO Auto-generated method stub
		return null;
	}

	private InitialStateGenerator newTilesGen() {
		// TODO Auto-generated method stub
		return null;
	}
}
