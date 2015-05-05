package com.example.firstapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{

    String classes[] = {"Camera", "Splash", "gfx", "GFXSurface", "BallMenu", "example5", "example6"};
    String classes2[] = {"Camera Wallpaper", "SplashLauncher", "Old Graphics", "New Graphics", "Menu for Ball", "example5", "example6"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1,classes2));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String ChE = classes[position];
        try{
            Class ourClass = Class.forName("com.example.h." + ChE);
            Intent ourIntent = new Intent(Menu.this, ourClass);
            startActivity(ourIntent);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}