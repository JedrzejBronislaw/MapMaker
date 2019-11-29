package mapMaker.generators;

import mapMaker.CA.CellularAutomation;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.InitialStateGeneratorManager;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.InitialStateGeneratorManager.InitialStateGeneratorType;
import mapMaker.map.Map;

public class CAMapGenerator implements MapGenerator {

	private CAGenOptions options;
	
	public void setOptions(CAGenOptions options) {
		if(options == null)
			this.options = new CAGenOptions();
		else
			this.options = options;
	}
	
	public CAMapGenerator() {
		options = new CAGenOptions();
	}
	
	@Override
	public Map generate(int width, int height) {
		
		Map map = new InitialStateGeneratorManager().get(InitialStateGeneratorType.Evenly).generate(width, height);
		
		CellularAutomation ca = new CellularAutomation(map);
		ca.setOptions(options.getCaOptions());
		map = ca.compute(options.generations);
		
		return map;
	}

}
