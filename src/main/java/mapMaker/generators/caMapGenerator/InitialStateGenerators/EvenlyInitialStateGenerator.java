package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import java.util.Random;

import mapMaker.map.FieldType;
import mapMaker.map.Map;

public class EvenlyInitialStateGenerator implements InitialStateGenerator {

	private Random r = new Random();
	
	@Override
	public Map generate(int width, int height) {
		Map map = new Map(width, height);
		
		for(int x=0; x < width; x++)
			for(int y=0; y < height; y++)
				map.set(x, y, randomField(0.5f));
//TODO				map.set(x, y, randomField(options.initialProbability));
		
		return map;
	}
	
	private FieldType randomField(float p) {
		if(r.nextFloat() <= p)
			return FieldType.Land;
		else
			return FieldType.Water;
	}
}
