package mapMaker.generators;

import java.util.Random;

import mapMaker.map.FieldType;
import mapMaker.map.Map;

public class RandomMapGenerator implements MapGenerator {

	private Random r;
	private FieldType[] fields = FieldType.values();

	@Override
	public Map generate(int width, int height) {
		Map map = new Map(width, height);
		r = new Random();
		
		for(int x=0; x < width; x++)
			for(int y=0; y < height; y++)
				map.set(x, y, randomField());

		
		return map;
	}

	private FieldType randomField() {
		return fields[r.nextInt(fields.length)];
	}

}
