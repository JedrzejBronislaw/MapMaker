package mapMaker.validators;

import javafx.scene.control.TextField;
import lombok.Setter;

public class IntegerValidator {

//	private List<TextField> fields = new LinkedList<TextField>();
	@Setter
	private int min = Integer.MIN_VALUE;
	@Setter
	private int max = Integer.MAX_VALUE;
	

	public IntegerValidator() {
	}
	public IntegerValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public void add(TextField field) {
		field.textProperty().addListener((obser, oldVal, newVal) ->{
			int val;
			try {
				val = Integer.parseInt(newVal);
			} catch(NumberFormatException e) {
				field.setText(oldVal);
				return;
			}
			
			if(val < min || val > max) {
				field.setText(oldVal);
			}
		});
		
	}
}
