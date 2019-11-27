package mapMaker.generators;

import java.util.Random;

import mapMaker.CA.CellularAutomation;
import mapMaker.map.FieldType;
import mapMaker.map.Map;

public class CAMapGenerator implements MapGenerator {

	private Random r;
	private FieldType[] fields = FieldType.values();
	
	
	@Override
	public Map generate(int width, int height) {
		Map map = new Map(width, height);
		r = new Random(5);
		
		for(int x=0; x < width; x++)
			for(int y=0; y < height; y++)
				map.set(x, y, randomField());
//				map.set(x, y, randomField(0.6f));

		new CellularAutomation(map).compute(100);
		
		return map;
	}

	private FieldType randomField() {
		return fields[r.nextInt(fields.length)];
	}

	private FieldType randomField(float p) {
		if(r.nextFloat() <= p)
			return FieldType.Land;
		else
			return FieldType.Water;
	}

}
