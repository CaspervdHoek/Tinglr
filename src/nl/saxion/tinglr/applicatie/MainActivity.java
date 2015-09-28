package nl.saxion.tinglr.applicatie;

import nl.saxion.tinglr.R;
import nl.saxion.tinglr.asynctasks.get.AccessTokenTask;
import nl.saxion.tinglr.asynctasks.get.AuthorizationTask;
import nl.saxion.tinglr.model.Model;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        final AccessTokenTask att = new AccessTokenTask(model, this);
        
        at.execute();
                
        webview.setWebViewClient(new WebViewClient() {
        	        	
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		String verifierToken = null;
        		if (url.startsWith("http://www.9gag.com")){
        			Log.d("token-url", url);
        			
        			verifierToken = Uri.parse(url).getQueryParameter("oauth_verifier");
        			        			
        			model.setVerifierToken(verifierToken);
        			
        			att.execute(verifierToken);
        			
        			webview.setVisibility(WebView.GONE);
        			
        			finish();
        			        			
        			return true;
        		}
        		return false;
        	}
        	
        });
                
    }
    
    public void startDashboardActivity(){
    	final Intent i = new Intent(this, DashboardActivity.class);
    	startActivity(i);
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
