package nl.saxion.tinglr.applicatie;

import java.util.List;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.asynctasks.get.GetDashboardTask;
import nl.saxion.tinglr.asynctasks.get.GetFavoritesTask;
import nl.saxion.tinglr.asynctasks.get.GetOwnBlogTask;
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

	private final int POST_TEXT = 101;
	private final int POST_QUOTE = 102;

	private ListView listViewTumblrPosts;
	private TumblrPostAdapter adapter;
	private TinglrApplication app;
	private Model model;
	private TextView eigenNaam;

	private ImageView eigenFoto;
	private ImageView imageRefresh;
	private List<Post> tumblrPosts;

	private ImageView textPost;
	private ImageView quotePost;

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

		textPost = (ImageView) findViewById(R.id.imageViewPost);
		quotePost = (ImageView) findViewById(R.id.imageViewQuote);

		eigenNaam.setText(customUser.getUserName());

		gdt = new GetDashboardTask(model, this, listViewTumblrPosts);

		gdt.execute();

		ProfilePhotoTask pft = new ProfilePhotoTask(model, eigenFoto);

		pft.execute(eigenNaam.getText() + "");

		initButtons();
	}

	private void initButtons() {
		imageRefresh.setClickable(true);
		imageRefresh.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				GetDashboardTask gdt = new GetDashboardTask(model,
//						DashboardActivity.this, listViewTumblrPosts);
//				gdt.execute();
				Log.d("Favorite", "Get favorites");
				GetFavoritesTask gft = new GetFavoritesTask(model,
						DashboardActivity.this, listViewTumblrPosts);
				gft.execute();
			}
		});

		textPost.setClickable(true);

		/**
		 * De onClick methode voor als een gebruiker een stuk tekst wil posten
		 */
		textPost.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("TEST", "Kom jij hier");
				// Popup scherm waar de gebruiker text kan invullen om
				// vervolgens te posten
				AlertDialog.Builder builder = new AlertDialog.Builder(
						DashboardActivity.this);
				builder.setTitle("Post");
				builder.setIcon(R.drawable.menu);
				builder.setMessage("Beasty Post");

				final EditText inputText = new EditText(DashboardActivity.this);
				inputText.setHint("Text");

				builder.setView(inputText);

				builder.setPositiveButton("Submit",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								String text = inputText.getText().toString();

								if (text != null && !text.isEmpty()) {
									PostTextPost post = new PostTextPost(model,
											text, POST_TEXT);
									post.execute();
								}

							}
						});

				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// Sluit de popup weer af
								dialog.dismiss();

							}
						});

				// Laat de popup zien als de gebruiker op de knop klinkt
				AlertDialog add = builder.show();
				add.show();

			}
		});

		/**
		 * De onClick methode als de gebruiker een quote wil posten
		 */
		quotePost.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Hier wordt de popup gemaakt om een quote te kunnen posten
				AlertDialog.Builder builder = new AlertDialog.Builder(
						DashboardActivity.this);
				builder.setTitle("Quote");
				builder.setIcon(R.drawable.qoute);
				builder.setMessage("Hottie Quote");

				final EditText inputText = new EditText(DashboardActivity.this);
				inputText.setHint("Text");

				builder.setView(inputText);

				builder.setPositiveButton("Submit",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								String text = inputText.getText().toString();
								// Controle om te kijken of de text niet leeg
								if (text != null && !text.isEmpty()) {
									PostTextPost post = new PostTextPost(model,
											text, POST_QUOTE);
									post.execute();

								}

							}
						});

				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();

							}
						});

				AlertDialog add = builder.show();
				add.show();

			}

		});
		
		eigenFoto.setClickable(true);
		eigenFoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GetOwnBlogTask gobt = new GetOwnBlogTask(model, DashboardActivity.this, listViewTumblrPosts);
				gobt.execute();
			}
		});

	}

}
