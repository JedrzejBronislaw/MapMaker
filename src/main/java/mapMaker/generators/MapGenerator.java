package mapMaker.generators;

import mapMaker.map.Map;

public interface MapGenerator {

	Map generate(int width, int height);
}
