package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import java.util.Random;

import mapMaker.map.FieldType;
import mapMaker.map.Map;
import mapMaker.optionInterfaces.Options;

public class CircleInitialStateGenerator implements InitialStateGenerator {

	private Random r = new Random();
	
	private CircleISGenOptions options = new CircleISGenOptions();

	private float centreX;
	private float centreY;
	
	@Override
	public void setOptions(Options options) {
		if (options instanceof CircleISGenOptions)
			this.options = (CircleISGenOptions) options;
		else
			this.options = new CircleISGenOptions();
	}
	
	@Override
	public Map generate(int width, int height) {
		Map map = new Map(width, height);		
		
		centreX = width * options.centreX;
		centreY = height * options.centreY;
		
		for(int x=0; x < width; x++) 
			for(int y=0; y < height; y++) 
				map.set(x, y, randomField(calculatePropability(x, y)));
		
		return map;
	}
	
	private float calculatePropability(float x, float y) {
		double distance = Math.sqrt(Math.pow(x-centreX,2)+Math.pow(y-centreY,2));
		
		if (distance > options.circleR)
			return 0;
		if (distance <= options.centreR)
			return 1;
		
		return (float) (1-((distance - options.centreR) / (options.circleR - options.centreR)));

	}

	private FieldType randomField(float p) {
		if(r.nextFloat() <= p)
			return FieldType.Land;
		else
			return FieldType.Water;
	}
}
