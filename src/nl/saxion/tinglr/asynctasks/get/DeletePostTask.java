package nl.saxion.tinglr.asynctasks.get;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.applicatie.DashboardActivity;
import nl.saxion.tinglr.asynctasks.post.PostTextPost;
import nl.saxion.tinglr.model.Model;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

public class DeletePostTask extends AsyncTask<Post, Void, Void> {
	
	private JumblrClient client;
	private Model model;
	private DashboardActivity activity;
	
	public DeletePostTask(Model model, DashboardActivity activity){
		this.model = model;
		this.client = model.getClient();
		this.activity = activity;
	}

	@Override
	protected Void doInBackground(Post... params) {
		final Post post = params[0];
		if(post.getBlogName().equals(client.user().getName())){
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Delete post");
			builder.setIcon(R.drawable.menu);
			builder.setMessage("Are you sure you want to delete this post?");

			builder.setPositiveButton("Delete",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							post.delete();
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
		return null;	
	}
	
}
