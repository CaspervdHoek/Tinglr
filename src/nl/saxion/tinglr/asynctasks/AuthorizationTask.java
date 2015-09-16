package nl.saxion.tinglr.asynctasks;

import nl.saxion.tinglr.applicatie.Applicatie;
import nl.saxion.tinglr.model.Model;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.User;

import android.os.AsyncTask;
import android.webkit.WebView;

public class AuthorizationTask extends AsyncTask<String, Void, String> {
	
	private Model model;
		
	private WebView webview;
	
	public AuthorizationTask(WebView webview, Model model){
		this.webview = webview;
		this.model = model;
	}
	public void init(){
		
	}


	@Override
	protected String doInBackground(String... params) {
		
		// Authenticate via OAuth
		JumblrClient client = model.getClient();
		
				// Make the request
				User user = client.user();
		return null;
	}

}
