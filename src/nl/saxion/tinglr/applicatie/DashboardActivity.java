package nl.saxion.tinglr.applicatie;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.User;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.model.CustomUser;
import nl.saxion.tinglr.model.Model;
import nl.saxion.tinglr.view.TumblrPostAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
		app = (TinglrApplication) getBaseContext().getApplicationContext();
		model = app.getModel();
		final CustomUser customUser = model.getUser();
		
		listViewTumblrPosts = (ListView) findViewById(R.id.listViewTumblrPosts);
		eigenNaam = (TextView) findViewById(R.id.eigenNaam);
		eigenFoto = (ImageView) findViewById(R.id.eigenFoto);
		bingButton = (ImageButton) findViewById(R.id.bingButton);
		eigenNaam.setText(customUser.getUserName());
		
		bingButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("test", "test");
				Log.d("name", customUser.getUserName());
			}
		});
	}
}
