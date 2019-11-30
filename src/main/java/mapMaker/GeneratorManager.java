package mapMaker;

import lombok.Getter;
import mapMaker.generators.CAMapGenerator;
import mapMaker.generators.MapGenerator;
import mapMaker.generators.RandomMapGenerator;
import mapMaker.optionInterfaces.OptionsController;
import mapMaker.view.View;

public class GeneratorManager {

	public enum Generator{
		Random(null),
		Generator1("CAGenOptions.fxml");
		
		@Getter
		private String optionsFXML;
		
		private Generator(String optionsFXML) {
			this.optionsFXML = optionsFXML;
		}	
	};
	
	static public MapGenerator get(Generator type) {
		switch (type) {
		case Random:
			return newRandomMapGenerator();
		case Generator1:
			return newCAMapGenerator();
		default:
			return new RandomMapGenerator();
		}
	}

	private static MapGenerator newRandomMapGenerator() {
		return new RandomMapGenerator();
	}

	private static MapGenerator newCAMapGenerator() {
		CAMapGenerator generator = new CAMapGenerator();
		
		OptionsController controller = (OptionsController) View.getGeneratorOptionsController(Generator.Generator1);
		
		generator.setOptions(controller.getOptions());
		
		return generator;
	}
}
