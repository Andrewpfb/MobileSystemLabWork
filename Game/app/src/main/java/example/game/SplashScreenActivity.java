package example.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by frost on 26.11.2017.
 */

public class SplashScreenActivity extends Activity {
    private Thread mSplashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        final SplashScreenActivity splashScreenActivity=this;
        ImageView sunImageView = (ImageView)findViewById(R.id.sun);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
        sunImageView.startAnimation(animation);
        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(5000);
                    }
                }
                catch(InterruptedException ex){
                }
                finish();
                Intent intent = new Intent();
                intent.setClass(splashScreenActivity, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.diagonaltranslate,R.anim.alpha);
            }
        };
        mSplashThread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}
