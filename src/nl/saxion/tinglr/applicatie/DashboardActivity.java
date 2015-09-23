package nl.saxion.tinglr.applicatie;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.User;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.model.Model;
import nl.saxion.tinglr.view.TumblrPostAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DashboardActivity extends Activity {

	private ListView listViewTumblrPosts;
	private TinglrApplication app;
	private Model model;
	private TextView eigenNaam;
	private ImageView eigenFoto;
	private ImageButton bingButton;
	private JumblrClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
		app = (TinglrApplication) getBaseContext().getApplicationContext();
		model = app.getModel();
//		client = model.getClient();
//		User user = client.user();
		
		listViewTumblrPosts = (ListView) findViewById(R.id.listViewTumblrPosts);
		eigenNaam = (TextView) findViewById(R.id.eigenNaam);
		eigenFoto = (ImageView) findViewById(R.id.eigenFoto);
		bingButton = (ImageButton) findViewById(R.id.bingButton);
		
//		eigenNaam.setText(model.getClient().user().getName());
	}
}
