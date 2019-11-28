package mapMaker.generators;

import java.util.Random;

import mapMaker.CA.CellularAutomation;
import mapMaker.map.FieldType;
import mapMaker.map.Map;

public class CAMapGenerator implements MapGenerator {

	private Random r;
	
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
		Map map = new Map(width, height);
		r = new Random();
		
		for(int x=0; x < width; x++)
			for(int y=0; y < height; y++)
				map.set(x, y, randomField(options.initialProbability));

		CellularAutomation ca = new CellularAutomation(map);
		ca.setOptions(options.getCaOptions());
		map = ca.compute(options.generations);
		
		return map;
	}

	private FieldType randomField(float p) {
		if(r.nextFloat() <= p)
			return FieldType.Land;
		else
			return FieldType.Water;
	}

}
