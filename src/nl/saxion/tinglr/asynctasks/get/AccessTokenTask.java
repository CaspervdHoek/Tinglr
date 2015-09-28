package nl.saxion.tinglr.asynctasks.get;


import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.User;

import nl.saxion.tinglr.applicatie.MainActivity;
import nl.saxion.tinglr.applicatie.TinglrApplication;
import nl.saxion.tinglr.model.CustomUser;
import nl.saxion.tinglr.model.Model;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.os.AsyncTask;
import android.sax.StartElementListener;
import android.util.Log;

public class AccessTokenTask extends AsyncTask<String, Void, String> {
	
	private Model model;
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private TinglrApplication app;
	private JumblrClient client;
	private User user;
	private CustomUser customUser;
	private MainActivity activity;
	
	public AccessTokenTask(Model model, MainActivity activity){
		this.model = model;
		provider = model.getProvider();
		consumer = model.getConsumer();
		client = model.getClient();
		this.activity = activity;
	}

	@Override
	protected String doInBackground(String... params) {
		
		try {
			Log.d("pre", "retrieveAccessToken");
			provider.retrieveAccessToken(consumer, params[0]);
			Log.d("Token", consumer.getToken());
			Log.d("secret", consumer.getTokenSecret());
			model.setClientToken(consumer.getToken(), consumer.getTokenSecret());
			
			user = client.user();
			
			Log.d("Username" , user.getName());
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		customUser = new CustomUser(user.getName());
		model.setUser(customUser);
		customUser.setFollowingCount(user.getFollowingCount());
		customUser.setLikeCount(user.getLikeCount());
		customUser.setBlogs(user.getBlogs());
		activity.startDashboardActivity();
		super.onPostExecute(result);
	}

}
