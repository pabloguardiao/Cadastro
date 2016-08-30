package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by android6519 on 29/08/16.
 */
public class VerSite extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site);

        WebView webView = (WebView) findViewById(R.id.vwweb);
        String site = getIntent().getDataString();
        webView.loadUrl(site);
    }
}
