package mapMaker.CA;

import java.util.function.Consumer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mapMaker.Progressive;
import mapMaker.map.FieldType;
import mapMaker.map.Map;

@RequiredArgsConstructor
public class CellularAutomation implements Progressive {

	@NonNull
	private Map map;
	
	private CAOptions options = new CAOptions();
	
	@Setter
	private Consumer<Float> progressListiner;

	public void setOptions(CAOptions options) {
		this.options = (options == null) ? new CAOptions() : options;
	}
	
	public Map compute(int iteration) {
		
		if(progressListiner != null)
			progressListiner.accept(0f);
		
		for(int i=0; i<iteration; i++) {
			map = compute();
			
			if(progressListiner != null)
				progressListiner.accept(((float)(i+1)/iteration));
		}
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
				
				if(neighbors >= options.upTreshold)
					levelUP(newMap, x, y);
				if(neighbors <= options.downThreshold)
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
