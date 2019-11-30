package mapMaker.generators;

import java.util.function.Consumer;

import lombok.Setter;
import mapMaker.CA.CellularAutomation;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.InitialStateGeneratorManager;
import mapMaker.map.Map;
import mapMaker.optionInterfaces.Optionable;
import mapMaker.optionInterfaces.Options;

public class CAMapGenerator implements MapGenerator, Optionable {

	private CAGenOptions options;
	
	@Setter
	private Consumer<Float> progressListiner;
	
	public void setOptions(Options options) {
		if(options instanceof CAGenOptions)
			this.options = (CAGenOptions) options;
		else
			this.options = new CAGenOptions();
	}
	
	public CAMapGenerator() {
		options = new CAGenOptions();
	}
	
	@Override
	public Map generate(int width, int height) {

		if(progressListiner != null)
			progressListiner.accept(0f);
		
		Map map = new InitialStateGeneratorManager().get(options.initialStateGeneratorType).generate(width, height);
		
		CellularAutomation ca = new CellularAutomation(map);
		ca.setProgressListiner(progressListiner);
		ca.setOptions(options.getCaOptions());
		map = ca.compute(options.generations);
		
		return map;
	}

}
