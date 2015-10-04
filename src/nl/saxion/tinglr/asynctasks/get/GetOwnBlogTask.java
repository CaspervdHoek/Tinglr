package nl.saxion.tinglr.asynctasks.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.applicatie.DashboardActivity;
import nl.saxion.tinglr.model.Model;
import nl.saxion.tinglr.view.TumblrPostAdapter;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;

import android.os.AsyncTask;
import android.widget.ListView;

/**
 * Task die de blog van de ingelogde gebruiker ophaald
 *
 */
public class GetOwnBlogTask extends AsyncTask<String, Void, List<Post>> {

	private Model model;
	private JumblrClient client;
	private TumblrPostAdapter adapter;
	private DashboardActivity activity;
	private ListView listview;
	private Map<String, String> options = new HashMap<String, String>();
	
	public GetOwnBlogTask(Model model, DashboardActivity activity, ListView listview){
		this.model = model;
		this.activity = activity;
		this.listview = listview;
		client = this.model.getClient();
		
		options.put("filter", "text");
	}
	
	
	/**
	 * Haalt een lijst met posts van de gebruiker zijn eigen blog op.
	 */
	@Override
	protected List<Post> doInBackground(String... params) {
		return client.blogPosts(client.user().getName(), options);
	}
	
	/**
	 * Laadt de blog van de gebruiker in de listview
	 */
	@Override
	protected void onPostExecute(List<Post> result) {
		super.onPostExecute(result);
		
		adapter = new TumblrPostAdapter(activity, R.layout.tumblrpost, result);
		listview.setAdapter(adapter);
	}

}
