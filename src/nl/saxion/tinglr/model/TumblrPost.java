package nl.saxion.tinglr.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TumblrPost {
	
	private CustomUser user;
	private String text;
	
	
	public TumblrPost(JSONObject tumblrPost){
		try {
			this.user = new CustomUser(tumblrPost.getJSONObject("user"));
			this.text = tumblrPost.getString("text");
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}
	
	// Constructor zonder JSON
	public TumblrPost(CustomUser user, String text){
		this.user = user;
		this.text = text;
	}
	
	public CustomUser getUser(){
		return user;
	}
	
	public String getText(){
		return text;
	}

}
