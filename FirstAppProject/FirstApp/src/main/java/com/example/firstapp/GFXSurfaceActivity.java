package com.example.firstapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import java.lang.Math;

public class GFXSurfaceActivity extends Activity implements OnTouchListener
{
    MyBringBackSurface ourSurfaceView;
    float x, y, sX, sY, fX, fY, dX, dY;
    float aniX, aniY, scaledX, scaledY;
    float scale = 10;
    Canvas canvas;
    int cX;
    int cY;
    int tx;
    int ty;
    double rand = 0;
    Bitmap test, plus, tar;
    boolean target;
    int score;
    int cons;
    int width;
    int height;
    float bounceCount;
    float hitCount;
    int radius;
    int trial;
    public static String bounces = "";

    float xx, yy;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = 0;
        dY = 0;
        ty = tx = 0;
        aniX = aniY = scaledX = scaledY = 0;
        score = 0;
        target = false;
        cons = 0;
        xx = 0;
        yy = 0;
        trial = 0;
        bounces = "";

        bounceCount = 0;
        radius = BallMenuActivity.radius;
        width = BallMenuActivity.screenWidth;
        height = BallMenuActivity.screenHeight;
        System.out.println(height);
        System.out.println(width);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(ourSurfaceView);

        test = BitmapFactory.decodeResource(getResources(),
                R.drawable.tennisball);
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.x);
        tar = BitmapFactory.decodeResource(getResources(), R.drawable.target);

        genBall();

    }

    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event)
    {

        try
        {
            Thread.sleep(60);
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        x = event.getX();
        y = event.getY();
        Display display = getWindowManager().getDefaultDisplay();
        // int width = display.getWidth(); // deprecated
        // int height = display.getHeight();

        wallStart();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                aniX = aniY = scaledX = scaledY = fX = fY = 0;
                cons = 0;
                bounceCount = 0;
                hitCount = 0;
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                wallRelease();
                dX = fX-sX;
                dY = fY-sY;

                scaledX = dX / scale;
                scaledY = dY / scale;



                x = 0;
                y = 0;
                break;

        }
        return true;
    }

    public void wallStart()
    {
        float rad = radius;
        if (x - rad <= 0)
        {
            x = rad;
        }
        if (x + rad >= width)
        {
            x = width - rad;
        }
        if (y - rad <= 0)
        {
            y = rad;
        }
        if (y + rad >= height)
        {
            y = height - rad;
        }

        // || x+rad >= canvas.getWidth()

    }

    private void wallRelease()
    {
        float rad = radius;
        if (fX - rad <= 0)
        {
            fX = rad;
        }
        if (fX + rad >= width)
        {
            fX = width - rad;
        }
        if (fY - rad <= 0)
        {
            fY = rad;
        }
        if (fY + rad >= height)
        {
            fY = height - rad;
        }

    }
    public void genBall()
    {

        aniX = aniY = scaledX = scaledY = fX = fY = 0;
        cons = 0;

        hitCount = 0;
        sX = (int) (Math.random() * width);
        sY = (int) (Math.random() * height);
        fX = (int) (Math.random() * width);
        fY = (int) (Math.random() * height);
        wallRelease();
        dX = fX-sX;
        dY = fY-sY;
        float slope =dY/dX;
        double angle = Math.atan(slope);

        scaledX = (float) (Math.cos(angle)*scale);
        scaledY = (float) (Math.sin(angle)*scale);
        if (dX<0)
            if (scaledX >0)
                scaledX = -scaledX;
        if (dX>0)
            if (scaledX <0)
                scaledX = -scaledX;
        if (dY<0)
            if (scaledY >0)
                scaledY = -scaledY;
        if (dY>0)
            if (scaledY < 0)
                scaledY = -scaledY;

        //scaledX = dX / scale;
        //scaledY = dY / scale;

        x = 0;
        y = 0;

    }
    public class MyBringBackSurface extends SurfaceView implements Runnable
    {
        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;
        Paint p;

        public MyBringBackSurface(Context context)
        {
            super(context);
            ourHolder = getHolder();
            p = new Paint();
            p.setColor(Color.parseColor("#FFF94D"));

            // TODO Auto-generated constructor stub
        }

        @Override
        public void run()
        {

            while (isRunning)
            {

                if (!ourHolder.getSurface().isValid())
                    continue;
                canvas = ourHolder.lockCanvas();
                int tt = canvas.getHeight();
                canvas.drawRGB(77, 139, 255);
                if (!target)
                {
                    genT();
                }

                if (x != 0 && y != 0)
                {
                    canvas.drawCircle(x, y, radius, p);
                    // canvas.drawBitmap(test, x - 36, y - 36, null);
                }
                if (sX != 0 && sY != 0)
                {
                    canvas.drawBitmap(plus, sX - 36, sY - 36, null);
                }

                xx = fX - aniX;
                yy = fY - aniY;
                edge();
                if (fX != 0 && fY != 0)
                {

                    // canvas.drawBitmap(test, xx - 36, yy - 36, null);
                    canvas.drawCircle(xx, yy, radius, p);
                    //canvas.drawBitmap(plus, fX - 36, fY - 36, null);
                }

//              Paint textPaint = new Paint();
//              textPaint.setARGB(50, 254, 10, 50);
//              textPaint.setTextAlign(Align.CENTER);
//              textPaint.setTextSize(50);
//
//
//              canvas.drawText("Bounce: " + bounceCount, 5, 5, textPaint);
                canvas.drawBitmap(tar, tx - tar.getWidth() / 2, ty - tar.getHeight() / 2, null);
                aniX = aniX + scaledX;
                aniY = aniY + scaledY;
                detectC();
                ourHolder.unlockCanvasAndPost(canvas);
            }
            // TODO Auto-generated method stub

        }

        public void genT()
        {
            tx = width/2;
            ty = height/2;

            //tx = (int) (Math.random() * canvas.getWidth());
            //ty = (int) (Math.random() * canvas.getHeight());


            target = true;
            //System.out.println("Why Am i Being Called");
        }

        public void edge()
        {
            float rad = radius;
            if (xx - rad + .1 <= 0 || xx + rad - .1 >= canvas.getWidth())
            {
                scaledX = scaledX * -1;
                bounceCount++;
                //System.out.println(bounceCount);
            }
            if (yy - rad + .1 <= 0 || yy + rad - .1 >= canvas.getHeight())
            {
                scaledY = scaledY * -1;
                bounceCount++;
                //System.out.println(bounceCount);
            }

        }

        public void detectC()
        {
            double bx = fX - aniX;
            double by = fY - aniY;
            double d = Math.sqrt(Math.pow(tx - bx, 2) + Math.pow(ty - by, 2));

            if (d <  (radius + (75/2))   )
            {
                hitCount++;
                score = ++score + cons++;
                target = false;
                trial++;
                //System.out.println(trial+ ":  "+bounceCount);
                BallMenuActivity.numBounce = BallMenuActivity.numBounce + trial+ ":  "+bounceCount +" \n";

                genBall();
                bounceCount = 0;

            }
        }

        public void pause()
        {
            isRunning = false;
            while (true)
            {
                try
                {
                    ourThread.join();
                } catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;

        }

        public void resume()
        {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

    }

}
