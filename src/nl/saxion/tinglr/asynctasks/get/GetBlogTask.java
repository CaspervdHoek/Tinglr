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

public class GetBlogTask extends AsyncTask<String, Void, List<Post>> {

	private Model model;
	private JumblrClient client;
	private TumblrPostAdapter adapter;
	private DashboardActivity activity;
	private ListView listview;
	private Map<String, String> options = new HashMap<String, String>();
	private String blogname;
	
	public GetBlogTask(Model model, DashboardActivity activity, ListView listview, String blogname){
		this.model = model;
		this.activity = activity;
		this.listview = listview;
		this.blogname = blogname;
		client = this.model.getClient();
		
		options.put("filter", "text");
	}
	
	
	@Override
	protected List<Post> doInBackground(String... params) {
		return client.blogPosts(blogname, options);
	}
	
	@Override
	protected void onPostExecute(List<Post> result) {
		super.onPostExecute(result);
		
		adapter = new TumblrPostAdapter(activity, R.layout.tumblrpost, result);
		listview.setAdapter(adapter);
	}

}