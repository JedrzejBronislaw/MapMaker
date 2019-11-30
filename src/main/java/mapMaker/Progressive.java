package mapMaker;

import java.util.function.Consumer;

public interface Progressive {
	void setProgressListiner(Consumer<Float> progressListiner);
}
