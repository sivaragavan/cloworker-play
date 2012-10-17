package command;

import org.json.JSONObject;

public interface Command {
	void execute();
	void setInputs(JSONObject json);
}
