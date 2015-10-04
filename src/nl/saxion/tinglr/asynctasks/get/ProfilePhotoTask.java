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

/**
 * Task die ervoor zorgt van een profielfoto aan de hand van een URL wordt geladen in een Bitmap, zodat deze kan worden
 * weergeven in imageviews binnen de app
 *
 */
public class ProfilePhotoTask extends AsyncTask<String, Void, Bitmap> {

	private JumblrClient client;
	private Model model;
	private ImageView profielfoto;
	
	public ProfilePhotoTask(Model model, ImageView profielfoto){
		this.model = model;
		this.profielfoto = profielfoto;
		client = model.getClient();
	}
	
	/**
	 * Haalt eerst de URL van de profielfoto van de meegegeven gebruiker op en maakt hier een Bitmap van.
	 */
	@Override
	protected Bitmap doInBackground(String... params) {
		String photoUrl = client.blogAvatar(params[0], 128);
		
		Bitmap myBitmap = null;
		
		InputStream input;
		try {
			input = new java.net.URL(photoUrl).openStream();
			myBitmap = BitmapFactory.decodeStream(input);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return myBitmap;
	}

	/**
	 * Laadt de Bitmap van de profielfoto in de imageview
	 */
	@Override
	protected void onPostExecute(Bitmap result) {
		profielfoto.setImageBitmap(result);		
		super.onPostExecute(result);
	}
}
