package nl.saxion.tinglr.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	
	private String userName;
	
	public User(JSONObject user){
		try {
			this.userName = user.getString("user");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// Constructor zonder JSON
	public User(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}

}
