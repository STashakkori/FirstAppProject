package com.example.firstapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.ViewGroup;
import android.os.Build;
import android.app.Activity;
import android.view.MenuInflater;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.example.firstapp.R.layout.activity_main);
        Button yourButton = (Button)findViewById(R.id.button_play_game);
        yourButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.example.firstapp.R.menu.main, menu);
        return true;
    }

    public void startGame(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void startHelp(){
        Intent intent = new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }

}

    /**
     * A placeholder fragment containing a simple view.

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }*/
