package mapMaker.generators.caMapGenerator.InitialStateGenerators;

import java.util.Random;

import mapMaker.map.FieldType;
import mapMaker.map.Map;
import mapMaker.optionInterfaces.Options;

public class TilesInitialStateGenerator implements InitialStateGenerator {

	private Random r = new Random();
	float[][] tiles;
	
	private TilesISGenOptions options = new TilesISGenOptions();
	
	@Override
	public void setOptions(Options options) {
		if(options instanceof TilesISGenOptions)
			this.options = (TilesISGenOptions) options;
		else
			this.options = new TilesISGenOptions();
	}
	
	@Override
	public Map generate(int width, int height) {
		Map map = new Map(width, height);
		
		createProbabilities();
		
		for(int x=0; x < width; x++)
			for(int y=0; y < height; y++)
				map.set(x, y, randomField(getProbability(x, y, width, height)));
		
		return map;
	}
	
	private void createProbabilities() {
		tiles = new float[options.tilesX][options.tilesY];
		
		for(int x=0; x<options.tilesX; x++)
			for(int y=0; y<options.tilesY; y++)
				tiles[x][y] = (r.nextFloat() * (options.highProbability-options.lowProbability)) + options.lowProbability;

//		for(int x=0; x<options.tilesX; x++) {
//			for(int y=0; y<options.tilesY; y++)
//				System.out.print(tiles[x][y] + "\t");
//			System.out.println();
//		}
//		System.out.println("---");
	}

	private float getProbability(int x, int y, int width, int height) {
		int y_ = (int) Math.floor(y*options.tilesY/height);
		int x_ = (int) Math.floor(x*options.tilesX/width);
		
		return tiles[x_][y_];
	}

	private FieldType randomField(float p) {
		if(r.nextFloat() <= p)
			return FieldType.Land;
		else
			return FieldType.Water;
	}
}
