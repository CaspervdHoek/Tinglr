package nl.saxion.tinglr.asynctasks.get;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;

import android.os.AsyncTask;

public class ReblogTask extends AsyncTask<Object, Void, Void> {

	@Override
	protected Void doInBackground(Object... params) {
		Post post = (Post) params[0];
		JumblrClient client = (JumblrClient) params[1];
		post.reblog(client.user().getName());		
		return null;
	}

}
