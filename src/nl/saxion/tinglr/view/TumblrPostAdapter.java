package nl.saxion.tinglr.view;

import java.util.ArrayList;
import java.util.List;

import com.tumblr.jumblr.types.PhotoPost;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.TextPost;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.applicatie.TinglrApplication;
import nl.saxion.tinglr.asynctasks.get.ProfilePhotoTask;
import nl.saxion.tinglr.model.Model;
import nl.saxion.tinglr.model.TumblrPost;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TumblrPostAdapter extends ArrayAdapter<Post> {
	
	private LayoutInflater inflater;
	private int resource;
	private Model model;
	private TinglrApplication app;

	public TumblrPostAdapter(Context context, int resource, List<Post> objects) {
		super(context, resource, objects);
		inflater = LayoutInflater.from(context);
		this.resource = resource;
		
		app = (TinglrApplication) context.getApplicationContext();
		model = app.getModel();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(resource, parent, false);
		}
		
		TextView userName = (TextView) convertView.findViewById(R.id.userName);
		TextView tumblrPostText = (TextView) convertView.findViewById(R.id.tumblrPostText);
		ImageView profielfoto = (ImageView) convertView.findViewById(R.id.profielFoto);
		
		Post post = getItem(position);
		
		userName.setText(post.getBlogName());
		
		if(post instanceof TextPost){
			tumblrPostText.setText(((TextPost) post).getBody());			
		} else if (post instanceof PhotoPost){
			tumblrPostText.setText(((PhotoPost) post).getCaption());			
		}
		
		ProfilePhotoTask pft = new ProfilePhotoTask(model, profielfoto);
		
		pft.execute(post.getBlogName());
		
		return convertView;
	}

}
