package mapMaker.generators;

import lombok.Getter;
import mapMaker.CA.CAOptions;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.InitialStateGeneratorManager.InitialStateGeneratorType;
import mapMaker.optionInterfaces.Options;

public class CAGenOptions implements Options{
	
	public int generations = 10;
	public InitialStateGeneratorType initialStateGeneratorType = InitialStateGeneratorType.Evenly;

	@Getter
	private CAOptions caOptions = new CAOptions();
}
