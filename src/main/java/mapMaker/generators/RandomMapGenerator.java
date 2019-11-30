package mapMaker.generators;

import java.util.Random;
import java.util.function.Consumer;

import lombok.Setter;
import mapMaker.map.FieldType;
import mapMaker.map.Map;

public class RandomMapGenerator implements MapGenerator {

	private Random r;
	private FieldType[] fields = FieldType.values();

	@Setter
	private Consumer<Float> progressListiner;

	@Override
	public Map generate(int width, int height) {
		Map map = new Map(width, height);
		r = new Random();

		if(progressListiner != null)
			progressListiner.accept(0f);
		
		for(int x=0; x < width; x++) {
			for(int y=0; y < height; y++)
				map.set(x, y, randomField());
			
			if(progressListiner != null)
				progressListiner.accept((float)(x+1)/width);
		}
		
		return map;
	}

	private FieldType randomField() {
		return fields[r.nextInt(fields.length)];
	}

}
