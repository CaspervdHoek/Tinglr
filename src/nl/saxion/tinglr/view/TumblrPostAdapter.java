package nl.saxion.tinglr.view;

import java.util.ArrayList;
import java.util.List;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.PhotoPost;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.QuotePost;
import com.tumblr.jumblr.types.TextPost;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.applicatie.DashboardActivity;
import nl.saxion.tinglr.applicatie.TinglrApplication;
import nl.saxion.tinglr.asynctasks.get.DeletePostTask;
import nl.saxion.tinglr.asynctasks.get.GetBlogTask;
import nl.saxion.tinglr.asynctasks.get.LikeTask;
import nl.saxion.tinglr.asynctasks.get.ProfilePhotoTask;
import nl.saxion.tinglr.asynctasks.get.ReblogTask;
import nl.saxion.tinglr.model.Model;
import nl.saxion.tinglr.model.TumblrPost;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter klasse voor Tumblrposts
 *
 */
public class TumblrPostAdapter extends ArrayAdapter<Post> {
	
	private LayoutInflater inflater;
	private int resource;
	private Model model;
	private TinglrApplication app;
	private Post post;
	private JumblrClient client;
	private DashboardActivity activity;

	public TumblrPostAdapter(Context context, int resource, List<Post> objects) {
		super(context, resource, objects);
		inflater = LayoutInflater.from(context);
		this.resource = resource;
		
		app = (TinglrApplication) context.getApplicationContext();
		model = app.getModel();
		client = model.getClient();
		
		this.activity = (DashboardActivity) context;
	}
	
	/**
	 * Maakt het volgende item klaar, zodat de gebruiker door zijn posts kan scrollen.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(resource, parent, false);
		}
		
		TextView userName = (TextView) convertView.findViewById(R.id.userName);
		TextView tumblrPostText = (TextView) convertView.findViewById(R.id.tumblrPostText);
		ImageView profielfoto = (ImageView) convertView.findViewById(R.id.profielFoto);
		final ImageView favoriteButton = (ImageView) convertView.findViewById(R.id.favoriteButton);
		ImageView reblogButton = (ImageView) convertView.findViewById(R.id.reblogButton);
		
		post = getItem(position);
		userName.setText(post.getBlogName());
		
		/**
		 * Haalt de profiel van de postende gebruiker op
		 */
		ProfilePhotoTask pft = new ProfilePhotoTask(model, profielfoto);
		
		pft.execute(post.getBlogName());
		
		profielfoto.setClickable(true);
		
		/**
		 * Zorgt ervoor dat de gebruiker naar de blog van deze blogger kan gaan
		 */
		profielfoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Profielfoto", "Clicked on profielfoto, go to their blog.");
			}
		});
		
		/**
		 * Zorgt ervoor dat de gebruiker de post kan favoriten
		 */
		favoriteButton.setClickable(true);
		favoriteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Favorite", "Favorite this post");
				favoriteButton.setImageResource(R.drawable.ic_action_favorited);
				LikeTask lt = new LikeTask();
				lt.execute(post);
			}
		});
		
		/**
		 * Zorgt ervoor dat de gebruiker de post kan rebloggen
		 */
		reblogButton.setClickable(true);
		reblogButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Reblog", "Reblog this post");
				ReblogTask rt = new ReblogTask();
				rt.execute(post, client);
			}
		});
<<<<<<< HEAD
				
		return convertView;
	}
	
=======
		
		/**
		 * Kijkt of de post geliked is zodat dit gepasd aangegeven wordt
		 */
		if (post.isLiked()){
			favoriteButton.setImageResource(R.drawable.ic_action_favorited);
		} else {
			favoriteButton.setImageResource(R.drawable.ic_action_favorite);
		}
		
		/**
		 * Kijkt van welke soort de post is zodat de juiste text weergeven kan worden.
		 */
		if(post instanceof TextPost){
			tumblrPostText.setText(((TextPost) post).getBody());			
		} else if (post instanceof PhotoPost){
			tumblrPostText.setText(((PhotoPost) post).getCaption());			
		} else if (post instanceof QuotePost){
			tumblrPostText.setText("\"" + ((QuotePost) post).getText() + "\"");
		}
		
		return convertView;
	}
	
	
>>>>>>> origin/master

}
