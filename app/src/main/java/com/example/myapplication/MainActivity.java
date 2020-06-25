package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.time.Instant;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class MainActivity extends AppCompatActivity {
    ImageView glass1,glass2,glass3,ball,table;
    ObjectAnimator animationGlass1X;
    ObjectAnimator animationGlass1Y;
    ObjectAnimator animationGlass2Y;
    ObjectAnimator animationGlass3X;
    ObjectAnimator animationGlass3Y;
    GifImageView curtains;
    TextView instructionText;
    double seconds=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //Sim please play the music till the movement of glasses
        MediaPlayer mysong;
        mysong = MediaPlayer.create(MainActivity.this, R.raw.attention);
        mysong.start();


            table=findViewById(R.id.table);
            //table.setVisibility(View.INVISIBLE);
            curtains=findViewById(R.id.curtains);
            final Handler handlerG = new Handler();
            handlerG.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    curtains.setVisibility(View.GONE);
                }
            }, 5600);

        glass1=findViewById(R.id.glass1);
        glass2=findViewById(R.id.glass2);
        glass3=findViewById(R.id.glass3);
        ball=findViewById(R.id.ball);
        instructionText=findViewById(R.id.instructionText);

        ball.setVisibility(View.INVISIBLE);
        instructionText.setVisibility(View.INVISIBLE);

        seconds=5.6;
        final Handler handlerS=new Handler();
        handlerS.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0)
                {
                    seconds=seconds-0.5;
                    handlerS.postDelayed(this,500);
                }
                else
                {
                    takepositions();
                    seconds=3;
                    final Handler handler=new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(seconds>0)
                            {
                                seconds--;
                                handler.postDelayed(this,1000);
                            }
                            else
                            {
                                instructionText.setVisibility(View.VISIBLE);
                                ball.setVisibility(View.VISIBLE);
                                showTheBall1();

                            }
                        }
                    });

                }
            }
        });

    }
    public void takepositions()
    {
        animationGlass1X = ObjectAnimator.ofFloat(glass1, "translationX", 1220f);
        animationGlass1Y = ObjectAnimator.ofFloat(glass1, "translationY", 1405f);
        animationGlass1X.setDuration(3000);
        animationGlass1X.start();
        animationGlass1Y.setDuration(3000);
        animationGlass1Y.start();


        animationGlass2Y = ObjectAnimator.ofFloat(glass2, "translationY", 1640f);
        animationGlass2Y.setDuration(3000);
        animationGlass2Y.start();

        animationGlass3X = ObjectAnimator.ofFloat(glass3, "translationX", -1250f);
        animationGlass3Y = ObjectAnimator.ofFloat(glass3, "translationY", 1405f);

        animationGlass3X.setDuration(3000);
        animationGlass3X.start();
        animationGlass3Y.setDuration(3000);
        animationGlass3Y.start();

    }

    public void showTheBall1()
    {


        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1640f, 1000f);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                glass2.setTranslationY(progress);
                // no need to use invalidate() as it is already present in             //the text view.
            }
        });
        valueAnimator.start();

        seconds=0.5;
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0)
                {
                    seconds=seconds-0.5;
                    handler.postDelayed(this,500);
                }
                else
                {
                    if(seconds==-2)
                        showTheBall2();
                    else {
                        seconds = seconds - 1;
                        handler.postDelayed(this,1000);
                    }
                }
            }
        });


    }


    public void showTheBall2()
    {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1000f, 1640f);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                glass2.setTranslationY(progress);
                // no need to use invalidate() as it is already present in             //the text view.
            }
        });
        valueAnimator.start();
        seconds=2;
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0)
                {
                    seconds=seconds-1;
                    handler.postDelayed(this,1000);
                }
                else
                {
                    ball.setVisibility(View.INVISIBLE);
                    movement1();
                }
            }
        });


    }
    public void movement1() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-1250f, -500f);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                glass3.setTranslationX(progress);
                // no need to use invalidate() as it is already present in             //the text view.
            }
        });
        valueAnimator.start();

        ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(1220f,470f);
        valueAnimator2.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        valueAnimator2.setDuration(2000);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                glass1.setTranslationX(progress);
                // no need to use invalidate() as it is already present in             //the text view.
            }
        });
        valueAnimator2.start();

        seconds=2;
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0)
                {
                    seconds=seconds-1;
                    handler.postDelayed(this,1000);
                }
                else
                {
                    movement2();
                }
            }
        });




    }

    public void movement2()
    {
        animateDiagonalPan21(glass2);
        animateDiagonalPan22(glass3);

        seconds=1.5;
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0)
                {
                    seconds=seconds-0.5;
                    handler.postDelayed(this,500);
                }
                else
                {
                    movement3();
                }
            }
        });
    }
    private void animateDiagonalPan21(View v) {

        float targetY=1805f;
        float targetX=360f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1640, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", 75, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }
    private void animateDiagonalPan22(View v) {

        float targetY=1260f;
        float targetX=-855f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1405, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", -500, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }
    public void movement3()
    {
        animateDiagonalPan31(glass2);
        animateDiagonalPan32(glass3);

        seconds=1.5;
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0)
                {
                    seconds=seconds-0.5;
                    handler.postDelayed(this,500);
                }
                else
                {
                    movement4();
                }
            }
        });
    }
    private void animateDiagonalPan31(View v) {

        float targetY=1640f;
        float targetX=35f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1805, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", 360, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }
    private void animateDiagonalPan32(View v) {

        float targetY=1405f;
        float targetX=-500f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1260, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", -855, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }

    public void  movement4()
    {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-500f,-1250f);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                glass3.setTranslationX(progress);
                // no need to use invalidate() as it is already present in             //the text view.
            }
        });
        valueAnimator.start();

        ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(470f,1220f);
        valueAnimator2.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        valueAnimator2.setDuration(2000);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                glass1.setTranslationX(progress);
                // no need to use invalidate() as it is already present in             //the text view.
            }
        });
        valueAnimator2.start();

        seconds=2;
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0)
                {
                    seconds=seconds-1;
                    handler.postDelayed(this,1000);
                }
                else
                {
                    movement5();
                }
            }
        });

    }

    public void movement5()
    {
        animateDiagonalPan51(glass2);
        animateDiagonalPan52(glass3);

        seconds=1.5;
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0)
                {
                    seconds=seconds-0.5;
                    handler.postDelayed(this,500);
                }
                else
                {
                    movement6();
                }
            }
        });

    }
    private void animateDiagonalPan51(View v) {

        float targetY=1805f;
        float targetX=-360f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1640, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", 75, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }
    private void animateDiagonalPan52(View v) {

        float targetY=1265f;
        float targetX=-870f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1405, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", -1250, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }
public void movement6()
{
    animateDiagonalPan61(glass2);
    animateDiagonalPan62(glass3);

    seconds=1.5;
    final Handler handler=new Handler();
    handler.post(new Runnable() {
        @Override
        public void run() {
            if(seconds>0)
            {
                seconds=seconds-0.5;
                handler.postDelayed(this,500);
            }
            else
            {
                movement7();
            }
        }
    });
}

    private void animateDiagonalPan61(View v) {

        float targetY=1640f;
        float targetX=30f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1805, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", -360, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }
    private void animateDiagonalPan62(View v) {

        float targetY=1405f;
        float targetX=-1250f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1260, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", -895, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }


public void movement7()
{
    animateDiagonalPan71(glass2);
    animateDiagonalPan72(glass1);

    seconds=1.5;
    final Handler handler=new Handler();
    handler.post(new Runnable() {
        @Override
        public void run() {
            if(seconds>0)
            {
                seconds=seconds-0.5;
                handler.postDelayed(this,500);
            }
            else
            {
                //visible buttons
                instructionText.setText("    Where is the ball ?");
            }
        }
    });

}
    private void animateDiagonalPan71(View v) {

        float targetY=1810f;
        float targetX=370f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1640, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", 30, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }
    private void animateDiagonalPan72(View v) {

        float targetY=1255f;
        float targetX=860f;
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY",1405, targetY);

        ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", 1220, targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(1000);
        animSetXY.start();
    }

}
