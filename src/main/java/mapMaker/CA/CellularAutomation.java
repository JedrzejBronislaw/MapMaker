package mapMaker.CA;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mapMaker.map.FieldType;
import mapMaker.map.Map;

@RequiredArgsConstructor
public class CellularAutomation {

	@NonNull
	private Map map;

//	private boolean hasBoundary = true; //TODO version without bounduries
	private byte liveThreshold = 5;
	private byte deathThreshold = 3;
	
	
	public Map compute(int iteration) {
		for(int i=0; i<iteration; i++)
			map = compute();
		
		return map;
	}
	
	public Map compute() {
		int w = map.getWidth();
		int h = map.getHeight();
		Map newMap = new Map(w, h);
		byte neighbors;
		
		newMap = map;//
		
		for(int x = 0; x<w; x++)
			for(int y = 0; y<h; y++) {
				neighbors = coutNeighbors(x,y);
				
				if(neighbors >= liveThreshold)
					levelUP(newMap, x, y);
				if(neighbors <= deathThreshold)
					levelDown(newMap, x, y);
			}
				
		
		return newMap;
	}

	private void levelUP(Map map, int x, int y) {
		map.set(x, y, FieldType.Land);
	}

	private void levelDown(Map map, int x, int y) {
		map.set(x, y, FieldType.Water);
	}

	private byte coutNeighbors(int x, int y) {
		byte n = 0;
		
		if(x > 0) {
			if (map.get(x-1, y) == FieldType.Land) n++;				
			if ((y > 0) && (map.get(x-1, y-1) == FieldType.Land)) n++;
			if ((y < map.getHeight()) && (map.get(x-1, y+1) == FieldType.Land)) n++;
		}
		
		if (x < map.getWidth()-1) {
			if (map.get(x+1, y) == FieldType.Land) n++;				
			if ((y > 0) && (map.get(x+1, y-1) == FieldType.Land)) n++;
			if ((y < map.getHeight()) && (map.get(x+1, y+1) == FieldType.Land)) n++;
		}
		
		if ((y > 0) && (map.get(x, y-1) == FieldType.Land)) n++;
		if ((y < map.getHeight()) && (map.get(x, y+1) == FieldType.Land)) n++;
		
		
		return n;
	}
}
