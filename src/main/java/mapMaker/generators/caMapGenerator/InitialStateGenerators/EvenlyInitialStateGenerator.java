package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import java.util.Random;

import mapMaker.map.FieldType;
import mapMaker.map.Map;
import mapMaker.optionInterfaces.Options;

public class EvenlyInitialStateGenerator implements InitialStateGenerator {

	private Random r = new Random();
	
	private EvenlyISGenOptions options = new EvenlyISGenOptions();
	
	@Override
	public void setOptions(Options options) {
		if(options instanceof EvenlyISGenOptions)
			this.options = (EvenlyISGenOptions) options;
		else
			this.options = new EvenlyISGenOptions();
	}
	
	
	@Override
	public Map generate(int width, int height) {
		Map map = new Map(width, height);
		
		for(int x=0; x < width; x++)
			for(int y=0; y < height; y++)
				map.set(x, y, randomField(options.probability));
		
		return map;
	}
	
	private FieldType randomField(float p) {
		if(r.nextFloat() <= p)
			return FieldType.Land;
		else
			return FieldType.Water;
	}
}
