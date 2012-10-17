package command;

import org.json.JSONObject;

public class FlipUpCommand implements Command {
	private Light theLight;

	public FlipUpCommand(Light light) {
		this.theLight = light;
	}

	public void execute() {
		theLight.turnOn();
	}

	@Override
	public void setInputs(JSONObject json) {
		// TODO Auto-generated method stub
		
	}
}