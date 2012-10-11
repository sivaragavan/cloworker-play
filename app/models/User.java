package models;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity("users")
public class User {

	@Id
	public ObjectId id;
	public String email;
	public String username;
	public String password;

	public User() {

	}

	public User(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public JSONObject toJSON() {
		JSONObject response = new JSONObject();
		try {
			response.put("id", id);
			response.put("email", email);
			response.put("username", username);			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
	}

	public String toString() {
		return toJSON().toString();
	}
}
