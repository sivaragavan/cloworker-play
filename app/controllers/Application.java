package controllers;

import models.Session;
import models.User;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import plugins.MongoPlugin;

public class Application extends Controller {

	// Views

	public static Result index() throws Exception {
		return ok(views.html.index.render());
	}

	public static Result dashboard() throws Exception {
		Http.Cookie cookie = request().cookies().get("cloworker-sessionId");
		if (cookie != null && !cookie.value().equals("")) {
			String sessionId = cookie.value();
			Session s = MongoPlugin.ds.get(Session.class, new ObjectId(sessionId));
			if (s == null)
				return redirect(routes.Application.index());

			User u = s.user;

			return ok(views.html.dashboard.render(s, u));
		} else {
			return redirect(routes.Application.index());
		}
	}

	// APIs

	public static Result createUser(String email, String username, String password) throws Exception {
		User u = MongoPlugin.ds.find(User.class).field("email").equal(email).get();
		if (u == null) {
			u = MongoPlugin.ds.find(User.class).field("username").equal(username).get();
			if (u == null) {
				u = new User(email, username, password);
				MongoPlugin.ds.save(u);
				Session session = new Session(u);
				MongoPlugin.ds.save(session);
				return ok(u.toJSON().put("result", true).put("sessionId", session.sessionId).toString());
			} else {
				return ok(new JSONObject().put("result", false).put("message", "Username already in use").toString());
			}
		} else {
			return ok(new JSONObject().put("result", false).put("message", "Email already in use").toString());
		}

	}

	public static Result loginUser(String username, String password) throws Exception {
		User u = MongoPlugin.ds.find(User.class).field("username").equal(username).get();
		if (u == null) {
			return ok(new JSONObject().put("result", false).put("message", "Username/Password does not exist").toString());
		} else {
			System.out.println(u.toString());
			if (u.password.equals(password)) {
				Session session = new Session(u);
				MongoPlugin.ds.save(session);
				return ok(session.toJSON().put("result", true).toString());
			} else {
				return ok(new JSONObject().put("result", false).put("message", "Username/Password does not exist").toString());
			}
		}
	}

	public static Result logoutUser(String sessionId) throws Exception {
		Session s = MongoPlugin.ds.get(Session.class, new ObjectId(sessionId));
		if (s != null)
			MongoPlugin.ds.delete(s);
		return ok(new JSONObject().put("result", true).toString());
	}
}