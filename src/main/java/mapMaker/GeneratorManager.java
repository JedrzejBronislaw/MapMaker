package mapMaker;

import mapMaker.generators.CAMapGenerator;
import mapMaker.generators.MapGenerator;
import mapMaker.generators.RandomMapGenerator;

public class GeneratorManager {

	public enum Generator{Random, Generator1};
	
	static public MapGenerator get(Generator type) {
		switch (type) {
		case Random:
			return new RandomMapGenerator();
		case Generator1:
			return new CAMapGenerator();
		default:
			return new RandomMapGenerator();
		}
	}
}
