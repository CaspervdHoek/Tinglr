package nl.saxion.tinglr.view;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.model.TumblrPost;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TumblrPostAdapter extends ArrayAdapter<TumblrPost> {
	
	private LayoutInflater inflater;
	private int resource;

	public TumblrPostAdapter(Context context, int resource, ArrayList<TumblrPost> objects) {
		super(context, resource, objects);
		inflater = LayoutInflater.from(context);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(resource, parent, false);
		}
		
		TextView userName = (TextView) convertView.findViewById(R.id.userName);
		TextView tumblrPostText = (TextView) convertView.findViewById(R.id.tumblrPostText);
		ImageView profielfoto = (ImageView) convertView.findViewById(R.id.profielFoto);
		
		TumblrPost tumblrPost = getItem(position);
		
		userName.setText(tumblrPost.getUser().getUserName());
		tumblrPostText.setText(tumblrPost.getText());
		
		return convertView;
	}

}
