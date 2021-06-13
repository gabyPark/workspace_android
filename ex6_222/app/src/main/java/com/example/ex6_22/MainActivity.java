package com.example.ex6_22;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtUrl;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    edtUrl = findViewById(R.id.edtUrl);
    web = findViewById(R.id.webView1);

        WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);
        webSet.setJavaScriptEnabled(true); //

        web.loadUrl("file:///android_asset/htmltext/boxOfficeDateLoad.html");


    web.setWebViewClient(new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
            edtUrl.setText(web.getUrl());
        }
    });

    Button btnGo = findViewById(R.id.btnGo);
    Button btnBack = findViewById(R.id.btnBack);

    btnGo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            web.loadUrl(edtUrl.getText().toString());
        }
    });

    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            web.goBack();
        }
    });


    }
}