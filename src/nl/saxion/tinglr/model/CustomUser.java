package nl.saxion.tinglr.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.tumblr.jumblr.types.Blog;

public class CustomUser {
	
	private String userName;
	private int followingCount;
	private int likeCount;
	private List<Blog> blogs;
	
	public CustomUser(JSONObject user){
		try {
			this.userName = user.getString("user");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// Constructor zonder JSON
	public CustomUser(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String name){
		this.userName = name;
	}
	
	public int getFollowingCount(){
		return followingCount;
	}
	
	public void setFollowingCount(int followingCount){
		this.followingCount = followingCount;
	}
	
	public int getLikegCount(){
		return likeCount;
	}
	
	public void setLikeCount(int likeCount){
		this.likeCount = likeCount;
	}
	
	public List<Blog> getBlogs(){
		return blogs;
	}
	
	public void setBlogs(List<Blog> blogs){
		this.blogs = blogs;
	}

}
