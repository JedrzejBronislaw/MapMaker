package mapMaker.generators;

import lombok.Getter;
import mapMaker.CA.CAOptions;

public class CAGenOptions {
	
	public float initialProbability = 0.5f;
	public int generations = 10;

	@Getter
	private CAOptions caOptions = new CAOptions();
}
