package nl.saxion.tinglr.asynctasks.get;

import com.tumblr.jumblr.types.Post;

import android.os.AsyncTask;

/**
 * Task die een meegegeven post liked
 *
 */
public class LikeTask extends AsyncTask<Post, Void, Void> {

	/**
	 * Liked met Jumblr de meegegeven post
	 */
	@Override
	protected Void doInBackground(Post... params) {
		params[0].like();
		return null;
	}

}
