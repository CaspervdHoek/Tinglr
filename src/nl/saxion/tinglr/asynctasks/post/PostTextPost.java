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
	private QuotePost quote;
	private int type;

	public PostTextPost(Model model, String text, int type) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.text = text;
		this.type = type;
		this.client = this.model.getClient();

	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			if (type == 101) {
				post = client.newPost(client.user().getName(), TextPost.class);
				post.setBody(text);
				post.save();
			} else if (type == 102) {
				quote = client.newPost(client.user().getName(), QuotePost.class);
				quote.setText(text);
				quote.save();
			}
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
