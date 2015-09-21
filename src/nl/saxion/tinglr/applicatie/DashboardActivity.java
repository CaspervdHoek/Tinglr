package nl.saxion.tinglr.applicatie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class DashboardActivity extends Activity {

	private ListView listViewTumblrPosts;
	private TextView realName, userName, following, followers, tweetCount;
	private MyApplication app;
	private Model model;
	private TweetAdapter adapter;
	private ImageView profilePhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
	}
}
