package mapMaker.generators;

import mapMaker.Progressive;
import mapMaker.map.Map;

public interface MapGenerator extends Progressive {

	Map generate(int width, int height);
}
