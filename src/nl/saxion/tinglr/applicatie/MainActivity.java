package nl.saxion.tinglr.applicatie;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.asynctasks.AuthorizationTask;
import nl.saxion.tinglr.model.Model;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

	private WebView webview;
	private TinglrApplication app;
	private Model model;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        app = (TinglrApplication) getBaseContext().getApplicationContext();
        model = app.getModel();
        
        webview = (WebView) findViewById(R.id.webView1);
        
        AuthorizationTask at = new AuthorizationTask(webview, model);
        
        at.execute();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
