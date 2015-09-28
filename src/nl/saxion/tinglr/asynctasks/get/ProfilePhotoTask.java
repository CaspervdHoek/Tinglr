package nl.saxion.tinglr.asynctasks.get;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import com.tumblr.jumblr.JumblrClient;

import nl.saxion.tinglr.model.Model;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ProfilePhotoTask extends AsyncTask<String, Void, Bitmap> {

	private JumblrClient client;
	private Model model;
	private ImageView profielfoto;
	
	public ProfilePhotoTask(Model model, ImageView profielfoto){
		this.model = model;
		this.profielfoto = profielfoto;
		client = model.getClient();
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		String photoUrl = client.blogAvatar(params[0], 512);
		
		Bitmap myBitmap = null;
		
		InputStream input;
		try {
			input = new java.net.URL(photoUrl).openStream();
			myBitmap = BitmapFactory.decodeStream(input);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myBitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		profielfoto.setImageBitmap(result);		
		super.onPostExecute(result);
	}
}
