package com.uet.trungthao.ailatrieuphu;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uet.trungthao.ailatrieuphu.db.DatabaseHelper;
import com.uet.trungthao.ailatrieuphu.model.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    RelativeLayout rlFirstScreen, rlSecondScreen, rlPlayingGame;
    private ImageView ivNewGame, ivHighScore;
    private Animation animMove1, animMove2;
    private DatabaseHelper db;
    private Integer level;
    private PlayFragment playFragment;
    private TextView tvCauHoi, tvCaseA, tvCaseB, tvCaseC, tvCaseD;
    private RelativeLayout rlCaseA, rlCaseB, rlCaseC, rlCaseD;
    private boolean isSelect;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initEvent();
    }

    private void init() {
        ivNewGame = (ImageView) findViewById(R.id.iv_new_game);
        ivHighScore = (ImageView) findViewById(R.id.iv_high_score);
        rlFirstScreen = (RelativeLayout) findViewById(R.id.rl_first_screen);
        rlSecondScreen = (RelativeLayout) findViewById(R.id.rl_second_screen);
        rlPlayingGame = (RelativeLayout) findViewById(R.id.rl_playing_screen);

        db = new DatabaseHelper(getApplicationContext());
        level = 1;
        playFragment = new PlayFragment();

        tvCaseA = (TextView) findViewById(R.id.tv_case_a);
        tvCaseB = (TextView) findViewById(R.id.tv_case_b);
        tvCaseC = (TextView) findViewById(R.id.tv_case_c);
        tvCaseD = (TextView) findViewById(R.id.tv_case_d);
        tvCauHoi = (TextView) findViewById(R.id.tv_cau_hoi);

        rlCaseA = (RelativeLayout) findViewById(R.id.rl_case_a);
        rlCaseB = (RelativeLayout) findViewById(R.id.rl_case_b);
        rlCaseC = (RelativeLayout) findViewById(R.id.rl_case_c);
        rlCaseD = (RelativeLayout) findViewById(R.id.rl_case_d);
    }

    private void initEvent() {
        ivNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animotionMove();
            }
        });

        rlCaseA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSelect) {
                    rlCaseA.setBackgroundResource(R.drawable.select);
                    isSelect = true;
                    checkCase(1, rlCaseA);
                }
            }
        });

        rlCaseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSelect) {
                    rlCaseB.setBackgroundResource(R.drawable.select);
                    isSelect = true;
                    checkCase(2, rlCaseB);
                }
            }
        });

        rlCaseC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSelect) {
                    rlCaseC.setBackgroundResource(R.drawable.select);
                    isSelect = true;
                    checkCase(3, rlCaseC);
                }
            }
        });

        rlCaseD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSelect) {
                    rlCaseD.setBackgroundResource(R.drawable.select);
                    isSelect = true;
                    checkCase(4, rlCaseD);
                }
            }
        });
    }

    private void animotionMove() {
        Animation move1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move1);
        move1.setAnimationListener(MainActivity.this);
        rlFirstScreen.startAnimation(move1);
        rlSecondScreen.setVisibility(View.VISIBLE);
        rlSecondScreen.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2));
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
//        getSupportFragmentManager().beginTransaction().add(R.id.container, playFragment).commitNow();
//        playFragment.setQuestion(db, level.toString());
        rlSecondScreen.setVisibility(View.GONE);
        rlSecondScreen.setVisibility(View.GONE);
        rlPlayingGame.setVisibility(View.VISIBLE);
        setQuestion(db, level.toString());
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void checkCase(final int caseSelect, final RelativeLayout rlCase) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (question.getTrueCase() == caseSelect) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rlCase.setBackgroundResource(R.drawable.selecttrue);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rlCase.setBackgroundResource(R.drawable.selectfalse);
                        }
                    });
                }
            }
        }).start();
    }

    public void setQuestion(DatabaseHelper db, String level) {
        question = db.getQuestion(level);
        tvCauHoi.setText(question.getQuestion());
        tvCaseA.setText(question.getCaseA());
        tvCaseB.setText(question.getCaseB());
        tvCaseC.setText(question.getCaseC());
        tvCaseD.setText(question.getCaseD());
    }
}
