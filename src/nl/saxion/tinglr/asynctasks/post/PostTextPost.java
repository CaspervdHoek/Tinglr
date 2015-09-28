package nl.saxion.tinglr.asynctasks.post;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.QuotePost;
import com.tumblr.jumblr.types.TextPost;

import nl.saxion.tinglr.model.Model;
import android.os.AsyncTask;

public class PostTextPost extends AsyncTask<Void, Void, Void> {
	
	private Model model;
	private String text;
	private JumblrClient client;
	private TextPost post;

	public PostTextPost(Model model, String text) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.text = text;
		this.client = this.model.getClient();
		
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			post = client.newPost(client.user().getName(), TextPost.class);
			post.setBody(text);
			post.save();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	


}
