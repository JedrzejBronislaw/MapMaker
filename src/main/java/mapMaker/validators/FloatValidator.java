package mapMaker.validators;

import javafx.scene.control.TextField;
import lombok.Setter;

public class FloatValidator {

//	private List<TextField> fields = new LinkedList<TextField>();
	@Setter
	private float min = Float.MIN_VALUE;
	@Setter
	private float max = Float.MAX_VALUE;
	@Setter
	private int length = 0;

	public FloatValidator() {
	}
	public FloatValidator(float min, float max) {
		this.min = min;
		this.max = max;
	}
	
	public void add(TextField field) {
		field.textProperty().addListener((obser, oldVal, newVal) ->{
			float val;
			
			if (length > 0 && newVal.length() > length) {
				field.setText(oldVal);
				return;
			}
				
			try {
				val = Float.parseFloat(newVal);
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
