package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import java.util.Random;

import mapMaker.map.FieldType;
import mapMaker.map.Map;
import mapMaker.optionInterfaces.Options;

public class GradientInitialStateGenerator implements InitialStateGenerator {

	private Random r = new Random();
	
	private GradientISGenOptions options = new GradientISGenOptions();

	@Override
	public void setOptions(Options options) {
		if(options instanceof GradientISGenOptions)
			this.options = (GradientISGenOptions) options;
		else
			this.options = new GradientISGenOptions();
	}
	
	@Override
	public Map generate(int width, int height) {
		Map map = new Map(width, height);
		float p;
		
		for(int x=0; x < width; x++) {
			p = calculatePropability(width, x);
			for(int y=0; y < height; y++)
				map.set(x, y, randomField(p));
		}
		
		return map;
	}
	
	private float calculatePropability(int width, int x) {
		return options.startProbability + ((options.endProbability - options.startProbability) / width * x);
	}

	private FieldType randomField(float p) {
		if(r.nextFloat() <= p)
			return FieldType.Land;
		else
			return FieldType.Water;
	}
}
