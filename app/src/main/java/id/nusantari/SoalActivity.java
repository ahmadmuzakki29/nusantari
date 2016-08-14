package id.nusantari;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by W1064 on 12/08/2016.
 */

public class SoalActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soal);

        WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        mClient client = new mClient();
        webView.setWebViewClient(client);
        webSettings.setJavaScriptEnabled(true);

        int id = getIntent().getIntExtra("id",0);
        String url;
        if(id==1) {
            url = "http://arasitc.com/Nusantari/tarimerak/";
        }else if(id==2){
            url = "http://arasitc.com/Nusantari/taripiring/";
        }else{
            url = "http://arasitc.com/Nusantari/TariPendet/";
        }
        webView.loadUrl(url);
    }

    private class mClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
