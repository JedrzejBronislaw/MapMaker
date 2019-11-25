package mapMaker.map;

import lombok.Getter;

public class Map {

	@Getter
	private int width, height;
	private FieldType[][] map;
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		map = new FieldType[width][height];
	}

	public FieldType get(int x, int y) {
		if (x >= 0 && x < width && y >=0 && y < height)
			return map[x][y];
		else 
			return null;
	}
	
	public boolean set(int x, int y, FieldType field) {
		if (x >= 0 && x < width && y >=0 && y < height) {
			map[x][y] = field;
			return true;
		} else 
			return false;
	}
}
