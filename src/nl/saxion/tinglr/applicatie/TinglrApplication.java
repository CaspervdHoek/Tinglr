package nl.saxion.tinglr.applicatie;

import nl.saxion.tinglr.model.Model;
import android.app.Application;

/**
 * Applicatie klasse voor de app. Alle klassen van de app kunnen bij deze app om zo globale informatie te verkrijgen.
 *
 */
public class TinglrApplication extends Application {
	
	/**
	 * Model van de app die globale informatie bijhoudt
	 */
	private Model model;
	
	/**
	 * methode die wordt aangeroepen als de app opstart. Er wordt een nieuw Model object aangemaakt.
	 */
	public void onCreate() {
		super.onCreate();
		model = new Model();		
	}
	
	/**
	 * Methode die het Model teruggeeft.
	 * @return Model model
	 */
	public Model getModel(){
		return model;
	}

}
