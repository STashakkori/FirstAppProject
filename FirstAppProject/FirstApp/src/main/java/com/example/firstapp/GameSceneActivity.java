package com.example.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

/**
 * Created by rt on 12/27/13.
 */
public class GameSceneActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String message = intent.getStringExtra(SignInActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setText(message);
        setContentView(com.example.firstapp.R.layout.activity_gamescene);
        setupActionBar();
    }

    private void setupActionBar(){

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(com.example.firstapp.R.menu.display_message, menu);
        return true;
    }
}