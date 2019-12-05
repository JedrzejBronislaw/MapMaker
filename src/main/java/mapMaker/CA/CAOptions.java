package mapMaker.CA;

import mapMaker.optionInterfaces.Options;

public class CAOptions implements Options{

	public enum Neighborhood{Moore, vonNeumann}
	
//	private boolean hasBoundary = true; 
	
	public byte downThreshold = 3;
	public byte upTreshold = 5;
	public Neighborhood neighborhood = Neighborhood.Moore;
	public boolean inSitu = true;

}
