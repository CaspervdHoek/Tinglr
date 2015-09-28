package nl.saxion.tinglr.applicatie;

import java.util.List;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.asynctasks.get.GetDashboardTask;
import nl.saxion.tinglr.asynctasks.get.ProfilePhotoTask;
import nl.saxion.tinglr.asynctasks.post.PostTextPost;
import nl.saxion.tinglr.model.CustomUser;
import nl.saxion.tinglr.model.Model;
import nl.saxion.tinglr.view.TumblrPostAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DashboardActivity extends Activity {

	private ListView listViewTumblrPosts;
	private TumblrPostAdapter adapter;
	private TinglrApplication app;
	private Model model;
	private TextView eigenNaam;
	
	private ImageView eigenFoto;
	private ImageView imageRefresh;
	private List<Post> tumblrPosts;
	private ImageView textPost;
	
	private GetDashboardTask gdt;
	
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
		imageRefresh = (ImageView) findViewById(R.id.refreshImage);
		textPost = (ImageView) findViewById(R.id.imageView1);
		
		eigenNaam.setText(customUser.getUserName());
				
		 gdt = new GetDashboardTask(model, this, listViewTumblrPosts);
		
		gdt.execute();		
		
		ProfilePhotoTask pft = new ProfilePhotoTask(model, eigenFoto);
		
		pft.execute(eigenNaam.getText() + "");
		
		imageRefresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("test", "test");
				Log.d("name", customUser.getUserName());
				GetDashboardTask gdt = new GetDashboardTask(model, DashboardActivity.this, listViewTumblrPosts);
				gdt.execute();
			}
		});
		
		initButtons();
	}
	
	private void initButtons(){
		textPost.setClickable(true);
		textPost.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("TEST", "Kom jij hier");
				AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
				builder.setTitle("Post");
				builder.setIcon(R.drawable.menu);
				builder.setMessage("Beasty Post");
				
				final EditText inputText = new EditText(DashboardActivity.this);
				inputText.setHint("Text");
				
				builder.setView(inputText);
				
				
				builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String text = inputText.getText().toString();
						PostTextPost post = new PostTextPost(model, text);
						post.execute();
						
					}
				});
				
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						
					}
				});
				
				
				AlertDialog add = builder.show();
				add.show();
				
			}
		});
		
	}
}
