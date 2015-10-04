package nl.saxion.tinglr.asynctasks.get;

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
import android.util.Log;
import android.webkit.WebView;

/**
 * Task die ervoor zorgt dat de gebruiker een inlogscherm krijgt, waar hij/zij zich bij tumblr kan inloggen.
 *
 */
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

	/**
	 * Haalt de URL op van de inlogpagina van Tumblr en geeft deze door aan de onPostExecute
	 */
	@Override
	protected String doInBackground(String... params) {
		
		String url = "";
		
		CommonsHttpOAuthProvider provider = model.getProvider();
		CommonsHttpOAuthConsumer consumer = model.getConsumer();
		try {
			url =  provider.retrieveRequestToken(consumer, model.getCallbackURL());
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
		
		return url;
		
	}
	
	/**
	 * Laadt de meegekregen URL in de webview
	 */
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		webview.loadUrl(result);
	}

}
