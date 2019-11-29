package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import mapMaker.map.Map;

public interface InitialStateGenerator {

	Map generate(int width, int height);
}
