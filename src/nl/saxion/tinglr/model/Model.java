package nl.saxion.tinglr.model;

import com.tumblr.jumblr.JumblrClient;

public class Model {
	
	private static final String OAUTH_REQUEST_URL = "http://www.tumblr.com/oauth/request_token";
	private static final String OAUTH_ACCESSTOKEN_URL = "http://www.tumblr.com/oauth/access_token";
	private static final String OAUT_AUTHORIZE_URL = "http://www.tumblr.com/oauth/authorize";
	private static final String OAUTH_CALLBACK_URL = "http://9gag.com";
	
	private static final String CONSUMER_KEY = "wDfnPKafcj7hGnhDleK8E7gnY7IP23gcnGCFIlpnTs6feCV2c6";
	private static final String CONSUMER_SECRET = " aQqb9B82P0cY9myrmYwGKiNWr488IXuWz5VNtWdgN7e2AgFpvm";
	
	private JumblrClient client;
	
	public Model(){
		client = new JumblrClient(CONSUMER_KEY, CONSUMER_SECRET);
	}
	
	public JumblrClient getClient(){
		return client;
	}

}
