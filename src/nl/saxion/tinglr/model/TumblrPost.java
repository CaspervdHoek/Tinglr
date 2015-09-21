package nl.saxion.tinglr.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TumblrPost {
	
	private User user;
	private String text;
	
	
	public TumblrPost(JSONObject tumblrPost){
		try {
			this.user = new User(tumblrPost.getJSONObject("user"));
			this.text = tumblrPost.getString("text");
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}
	
	// Constructor zonder JSON
	public TumblrPost(User user, String text){
		this.user = user;
		this.text = text;
	}
	
	public User getUser(){
		return user;
	}
	
	public String getText(){
		return text;
	}

}
