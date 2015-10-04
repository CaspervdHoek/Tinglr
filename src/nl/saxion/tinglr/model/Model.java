package nl.saxion.tinglr.model;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

import com.tumblr.jumblr.JumblrClient;

/**
 * Model klasse die alle belangrijke en globaal beschikbare informatie bijhoud
 *
 */
public class Model {
	
	private static final String OAUTH_REQUEST_URL = "http://www.tumblr.com/oauth/request_token";
	private static final String OAUTH_ACCESSTOKEN_URL = "http://www.tumblr.com/oauth/access_token";
	private static final String OAUT_AUTHORIZE_URL = "http://www.tumblr.com/oauth/authorize";
	private static final String OAUTH_CALLBACK_URL = "http://www.9gag.com";
	private static final String CONSUMER_KEY = "wDfnPKafcj7hGnhDleK8E7gnY7IP23gcnGCFIlpnTs6feCV2c6";
	private static final String CONSUMER_SECRET = "aQqb9B82P0cY9myrmYwGKiNWr488IXuWz5VNtWdgN7e2AgFpvm";
	
	private String verifierToken;
	
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private JumblrClient client;
	private CustomUser user;
	
	public Model(){
		client = new JumblrClient(CONSUMER_KEY, CONSUMER_SECRET);
		provider = new CommonsHttpOAuthProvider(OAUTH_REQUEST_URL, OAUTH_ACCESSTOKEN_URL, OAUT_AUTHORIZE_URL);
		consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	}
	
	public JumblrClient getClient(){
		return client;
	}
	
	public CommonsHttpOAuthProvider getProvider(){
		return provider;
	}
	
	public CommonsHttpOAuthConsumer getConsumer(){
		return consumer;
	}
	
	public String getCallbackURL(){
		return OAUTH_CALLBACK_URL;
	}
	
	public String getVerifierToken(){
		return verifierToken;
	}
	
	public void setVerifierToken(String verifier){
		this.verifierToken = verifier;
	}
	
	public void setClientToken(String oauthToken, String oauthSecret){
		client.setToken(oauthToken, oauthSecret);
	}
	
	public CustomUser getUser(){
		return user;
	}
	
	public void setUser(CustomUser user){
		this.user = user;
	}

}
