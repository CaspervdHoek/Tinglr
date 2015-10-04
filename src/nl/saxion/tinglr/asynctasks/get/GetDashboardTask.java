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
 * Haalt de dashboard van de gebruiker op, i.e. de meest recente posts van mensen die hij/zij volgt.
 *
 */
public class GetDashboardTask extends AsyncTask<String, Void, List<Post>>{
	
	private Model model;
	private JumblrClient client;
	private TumblrPostAdapter adapter;
	private DashboardActivity activity;
	private ListView listview;
	private Map<String, String> options = new HashMap<String, String>();
	
	public GetDashboardTask(Model model, DashboardActivity activity, ListView listview){
		this.model = model;
		this.activity = activity;
		this.listview = listview;
		client = this.model.getClient();
		
		options.put("filter", "text");
	}
	
	/**
	 * Haalt met behulp van Jumblr een lijst van alle dashboardposts van de gebruiker op.
	 */
	@Override
	protected List<Post> doInBackground(String... params) {
		return client.userDashboard(options);
	}
	
	/**
	 * Zet de dashboardposts in de listview
	 */
	@Override
	protected void onPostExecute(List<Post> result) {
		super.onPostExecute(result);
		
		adapter = new TumblrPostAdapter(activity, R.layout.tumblrpost, result);
		listview.setAdapter(adapter);
	}

}
