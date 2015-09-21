package nl.saxion.tinglr.applicatie;

import nl.saxion.tinglr.model.Model;
import android.app.Application;

public class TinglrApplication extends Application {
	
	private Model model;
	
	public void onCreate() {
		super.onCreate();
		model = new Model();		
	};
	
	public Model getModel(){
		return model;
	}

}
