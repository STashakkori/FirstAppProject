package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BallMenuActivity extends Activity implements View.OnClickListener
{

    ToggleButton tb;
    Button bb;
    Button ub;

    EditText x1;
    EditText x2;
    EditText y1;
    EditText y2;
    int yc2;
    int yc1;
    int xc2;
    int xc1;
    public static int screenHeight;
    public static int screenWidth;
    public static int radius;
    public static int scale;
    public static TextView t;
    static String numBounce;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        numBounce = "Bounces: \n";
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ballmenu);

        radius=1;
        scale=20;
        bb = (Button) findViewById(R.id.start);
        ub = (Button) findViewById(R.id.update);
        x1 = (EditText) findViewById(R.id.x1);
        x2 = (EditText) findViewById(R.id.Scale);
        t = (TextView)findViewById(R.id.bounces);


        bb.setOnClickListener(this);
        ub.setOnClickListener(this);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
        System.out.println(screenHeight);
        System.out.println(screenWidth);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.start:
                xc1 = Integer.parseInt(x1.getText().toString());
                radius = xc1;
                scale = Integer.parseInt(x2.getText().toString());
                //yc1 = Integer.parseInt(y1.getText().toString());
                //yc2 = Integer.parseInt(y2.getText().toString());
                numBounce = "Bounces: \n";
                //Intent openMainActivity = new Intent("android.intent.action.GFXSurface");
                Intent openBallActivity = new Intent(this, GFXSurfaceActivity.class);

                startActivity(openBallActivity);

                break;
            case R.id.update:

                t.setText("" +numBounce);
                break;


        }


    }

}
