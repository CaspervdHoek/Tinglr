package nl.saxion.tinglr.asynctasks.get;

import com.tumblr.jumblr.types.Post;

import android.os.AsyncTask;

public class LikeTask extends AsyncTask<Post, Void, Void> {

	@Override
	protected Void doInBackground(Post... params) {
		params[0].like();
		return null;
	}

}
