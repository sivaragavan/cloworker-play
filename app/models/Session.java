package models;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

@Entity("sessions")
public class Session {

	@Id
	public ObjectId sessionId;
	@Reference
	public User user = new User();

	public Session() {
	}

	public Session(User user) {
		this.user = user;
	}

	public String toString() {
		return toJSON().toString();
	}

	public JSONObject toJSON() {
		JSONObject response = new JSONObject();
		try {
			response.put("sessionId", sessionId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
	}

}
