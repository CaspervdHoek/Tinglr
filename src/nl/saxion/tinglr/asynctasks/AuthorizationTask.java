package nl.saxion.tinglr.asynctasks;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import nl.saxion.tinglr.applicatie.TinglrApplication;
import nl.saxion.tinglr.model.Model;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.User;

import android.os.AsyncTask;
import android.webkit.WebView;

public class AuthorizationTask extends AsyncTask<String, Void, String> {
	
	private Model model;
	private CommonsHttpOAuthConsumer consumer;
	private CommonsHttpOAuthProvider provider;
		
	private WebView webview;
	
	public AuthorizationTask(WebView webview, Model model){
		this.webview = webview;
		this.model = model;
		provider = model.getProvider();
		consumer = model.getConsumer();
	}

	@Override
	protected String doInBackground(String... params) {
		
		String url = null;
				
		// Authenticate via OAuth
		//JumblrClient client = model.getClient();
		CommonsHttpOAuthProvider provider = model.getProvider();
		CommonsHttpOAuthConsumer consumer = model.getConsumer();
		try {
			url = provider.retrieveRequestToken(consumer, model.getCallbackURL());
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
		
		return url;
		
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		webview.loadUrl(result);
	}

}
