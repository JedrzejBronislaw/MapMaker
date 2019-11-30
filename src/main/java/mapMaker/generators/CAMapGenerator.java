package mapMaker.generators;

import mapMaker.CA.CellularAutomation;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.InitialStateGeneratorManager;
import mapMaker.map.Map;
import mapMaker.optionInterfaces.Optionable;
import mapMaker.optionInterfaces.Options;

public class CAMapGenerator implements MapGenerator, Optionable {

	private CAGenOptions options;
	
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
		
		Map map = new InitialStateGeneratorManager().get(options.initialStateGeneratorType).generate(width, height);
		
		CellularAutomation ca = new CellularAutomation(map);
		ca.setOptions(options.getCaOptions());
		map = ca.compute(options.generations);
		
		return map;
	}

}
