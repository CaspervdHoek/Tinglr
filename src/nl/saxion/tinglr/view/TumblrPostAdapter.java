package nl.saxion.tinglr.view;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.tinglr.model.TumblrPost;
import android.content.Context;
import android.widget.ArrayAdapter;

public class TumblrPostAdapter extends ArrayAdapter<TumblrPost> {

	public TumblrPostAdapter(Context context, int resource, ArrayList<TumblrPost> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

}
