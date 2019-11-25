package mapMaker.viewers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mapMaker.map.Map;

public class MapViewer {

	double size = 1;
	
	public Canvas createView(Map map) {
		Canvas canvas = new Canvas();
		GraphicsContext c = canvas.getGraphicsContext2D();
			
		int w = map.getWidth();
		int h = map.getHeight();
		canvas.setWidth(w*size);
		canvas.setHeight(h*size);
		
		double x_, y_, w_, h_;
		
		c.setFill(Color.AQUA);
		for(int x=0; x<w; x++)
			for(int y=0; y<h; y++) {
				x_ = x*size;
				y_ = y*size;
				w_ = size;
				h_ = size;
				switch (map.get(x, y)) {
				case Land:
					c.setFill(Color.rgb(0, 200, 0));
					break;
				case Water:
					c.setFill(Color.BLUE);
					break;
				default:
					c.setFill(Color.BLACK);
					break;
				}
				
				c.fillRect(x_, y_, w_, h_);
			}
		
		return canvas;
	}

}
