package nl.saxion.tinglr.asynctasks.get;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;

import android.os.AsyncTask;

/**
 * Task voor het rebloggen van een meegegeven post
 *
 */
public class ReblogTask extends AsyncTask<Object, Void, Void> {

	/**
	 * Reblogt m.b.v. Jumblr een meegegeven post
	 */
	@Override
	protected Void doInBackground(Object... params) {
		Post post = (Post) params[0];
		JumblrClient client = (JumblrClient) params[1];
		post.reblog(client.user().getName());		
		return null;
	}

}
