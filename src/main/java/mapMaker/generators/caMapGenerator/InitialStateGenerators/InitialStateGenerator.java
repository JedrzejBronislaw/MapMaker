package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import mapMaker.map.Map;
import mapMaker.optionInterfaces.Optionable;

public interface InitialStateGenerator extends Optionable {

	Map generate(int width, int height);
}
