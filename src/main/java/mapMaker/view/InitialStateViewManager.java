package mapMaker.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import mapMaker.GeneratorManager.Generator;
import mapMaker.generators.caMapGenerator.InitialStateGenerators.InitialStateGeneratorManager.InitialStateGeneratorType;
import mapMaker.tools.MyFXMLLoader;
import mapMaker.tools.MyFXMLLoader.NodeAndController;

public class InitialStateViewManager {

	private static final String viewDir = "caMapGenerator/";
	static private Map<InitialStateGeneratorType, NodeAndController> initialStateGeneratorsOptionsViews = new HashMap<InitialStateGeneratorType, NodeAndController>(Generator.values().length);
	
	public static void load() {
		loadGeneratorOpitonsViews();
	}
	
	private static void loadGeneratorOpitonsViews() {
		InitialStateGeneratorType[] generators = InitialStateGeneratorType.values();
		NodeAndController nac;
		MyFXMLLoader loader = new MyFXMLLoader();
		
		for(InitialStateGeneratorType g : generators) {
			if (g.getOptionsFXML() == null) 
				nac = null;
			else {
				try {
					nac = loader.create(viewDir + g.getOptionsFXML());
				} catch (IOException e) {
					e.printStackTrace();
					nac = null;
				}
			}
			initialStateGeneratorsOptionsViews.put(g, nac);
		}
	}
	
	public static Initializable getGeneratorOptionsController(InitialStateGeneratorType g) {
		NodeAndController nac = initialStateGeneratorsOptionsViews.get(g);

		if (nac != null)
			return nac.getController();
		else
			return null;	
	}

	public static Node getGeneratorView(InitialStateGeneratorType g) {
		NodeAndController nac = initialStateGeneratorsOptionsViews.get(g);

		if (nac != null) {
			return nac.getNode();
		} else
			return null;
	}
}
