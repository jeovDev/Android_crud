package com.example.jeovannelugo.phishbook;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class My_Facebook extends AppCompatActivity {

    private android.webkit.WebView m;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__facebook);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        m = (android.webkit.WebView) findViewById(R.id.web);
        WebSettings webSettings = m.getSettings();
        webSettings.setJavaScriptEnabled(true);
        m.setWebViewClient(new WebViewClient());

        m.loadUrl("https://m.facebook.com/");

    }

    @Override
    public void onBackPressed() {
    if(m.canGoBack()){
        m.goBack();
    }else {
        super.onBackPressed();
    }


    }
}
