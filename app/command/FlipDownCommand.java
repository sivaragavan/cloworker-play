package command;

import org.json.JSONObject;

public class FlipDownCommand implements Command {
	private Light theLight;

	public FlipDownCommand(Light light) {
		this.theLight = light;
	}

	public void execute() {
		theLight.turnOff();
	}

	@Override
	public void setInputs(JSONObject json) {
		// TODO Auto-generated method stub
		
	}
}