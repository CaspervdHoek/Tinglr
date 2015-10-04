package nl.saxion.tinglr.asynctasks.post;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.QuotePost;
import com.tumblr.jumblr.types.TextPost;

import nl.saxion.tinglr.model.Model;
import android.os.AsyncTask;

/**
 * Task die een textpost of quotepost kan maken op de blog van de gebruiker.
 *
 */
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

	/**
	 * Controleert of de meegegeven tekst in een textpost moet komen of in een quotepost, en post dit vervolgens
	 * m.b.v. Jumblr
	 */
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
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

		return null;
	}

}
