package mapMaker.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.Initializable;
import mapMaker.GeneratorManager.Generator;

public class View {

	static private Map<Generator, Window> generatorsOptionsWindows = new HashMap<Generator, Window>(Generator.values().length);
	
	public static void load() {
		loadGeneratorOpitonsWindows();
	}
	
	private static void loadGeneratorOpitonsWindows() {
		Generator[] generators = Generator.values();
		Window window;
		
		for(Generator g : generators) {
			if (g.getOptionsFXML() == null) 
				window = null;
			else {
				try {
					window = new Window(g.getOptionsFXML());
					window.setTitle(g.toString() + " options");
				} catch (IOException | IllegalArgumentException e) {
					e.printStackTrace();
					window = null;
				}
			}
			generatorsOptionsWindows.put(g, window);
		}
	}
	
	public static Initializable getGeneratorOptionsController(Generator g) {
		Window window = generatorsOptionsWindows.get(g);

		if (window != null)
			return window.getController();
		else
			return null;	
	}

	public static Window openGeneratorOptions(Generator g) {
		Window window = generatorsOptionsWindows.get(g);

		if (window != null) {
			window.show();
			return window;
		} else
			return null;
	}
}
