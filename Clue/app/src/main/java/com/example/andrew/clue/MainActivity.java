package com.example.andrew.clue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView browser = (WebView)findViewById(R.id.web_view);
        browser.loadUrl("file:///android_asset/clue.html");
    }
}
