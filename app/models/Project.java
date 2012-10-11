package models;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

@Entity("projects")
public class Project {

	@Id
	public ObjectId projectId;
	@Reference
	public User user = new User();

	public Project() {
	}

	public Project(User user) {
		this.user = user;
	}

	public String toString() {
		return toJSON().toString();
	}

	public JSONObject toJSON() {
		JSONObject response = new JSONObject();
		try {
			response.put("sessionId", projectId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
	}

}
