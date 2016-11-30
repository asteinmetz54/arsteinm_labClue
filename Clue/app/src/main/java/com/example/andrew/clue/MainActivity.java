package com.example.andrew.clue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_USERNAME = "com.example.andrew.clue.USERNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playGame(View view){
        String username = "";
        EditText userInput = (EditText)findViewById(R.id.editText_name);
        username = userInput.getText().toString();
        if(username.matches("")){
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Intent intent = new Intent(this, ClueGame.class);
            intent.putExtra(EXTRA_USERNAME, username);
            startActivity(intent);
        }
    }
}
