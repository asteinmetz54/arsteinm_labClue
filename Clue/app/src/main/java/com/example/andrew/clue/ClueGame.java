package com.example.andrew.clue;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ClueGame extends AppCompatActivity {
    private String gameRecord = "none";
    static final String GAME_RECORD = "record";


    public class WebAppInterface{
        Context mContext;

        WebAppInterface(Context c){
            mContext = c;
        }

        @JavascriptInterface
        public void restartClue(String record){
            gameRecord = record;
            finish();
            //Intent intent1;
            //intent1 = new Intent(mContext, MainActivity.class);
            //startActivity(intent1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clue_game);
        //get username entered from main activity
        Intent intent = getIntent();
        final String username = intent.getStringExtra(MainActivity.EXTRA_USERNAME);

        //load webview
        final WebView browser = (WebView)findViewById(R.id.web_view);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");
        browser.loadUrl("file:///android_asset/clue.html");
        browser.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                browser.loadUrl("javascript:record('"+ gameRecord + "')");
                browser.loadUrl("javascript:init('" + username +"')");
            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the record of the game
        savedInstanceState.putString(GAME_RECORD, gameRecord);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameRecord = savedInstanceState.getString(GAME_RECORD);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit Game")
                .setMessage("Are you sure you want to exit the game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
