package com.uet.trungthao.ailatrieuphu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.uet.trungthao.ailatrieuphu.animation.MyAnimation;

public class SlashActivity extends AppCompatActivity implements Animation.AnimationListener {

    private static final String TAG = SlashActivity.class.getSimpleName();
    RelativeLayout rlFirstScreen, rlSecondScreen;
    private ImageView ivNewGame, ivHighScore;
    private MyAnimation myAnim;
    private boolean isFirstPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        addController();
        addEvent();
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        startActivity(new Intent(SlashActivity.this, MainActivity.class));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    private void addController() {
        ivNewGame = (ImageView) findViewById(R.id.iv_new_game);
        ivHighScore = (ImageView) findViewById(R.id.iv_high_score);
        rlFirstScreen = (RelativeLayout) findViewById(R.id.rl_first_screen);
        rlSecondScreen = (RelativeLayout) findViewById(R.id.rl_second_screen);

        myAnim = new MyAnimation(getApplicationContext());
    }

    private void addEvent() {
        ivNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "start");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.chungta);
                mediaPlayer.start();
                myAnim.move(rlFirstScreen, rlSecondScreen, SlashActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirstPlay) {
            isFirstPlay = false;
        } else {
            myAnim.move(rlSecondScreen, rlFirstScreen, null);
        }
    }
}
