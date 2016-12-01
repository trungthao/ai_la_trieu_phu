package com.uet.trungthao.ailatrieuphu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uet.trungthao.ailatrieuphu.animation.MyAnimation;
import com.uet.trungthao.ailatrieuphu.common.CommonVLs;
import com.uet.trungthao.ailatrieuphu.activity_helper.AudienceHelpActivity;
import com.uet.trungthao.ailatrieuphu.class_helper.GameHelper;
import com.uet.trungthao.ailatrieuphu.activity_helper.RelativeActivity;
import com.uet.trungthao.ailatrieuphu.model.Question;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by JiH on 13/10/2016.
 */

public class MainActivity extends Activity {

    private static final long TIME_SLEEP = 1000;
    private static final int START_TIME_COUNT = 90;
    private static final int DELAY_AUDIO_TIME = 700;
    private static final String TAG = MainActivity.class.getSimpleName();
    private final String backgroudLevelColor = "#FF9800";
    private final String transparentLevelColor = "#07000000";
    private final String importantLevelColor = "#4CAF50";

    private RelativeLayout rlCaseA, rlCaseB, rlCaseC, rlCaseD;
    private TextView tvQuestion, tvCaseA, tvCaseB, tvCaseC, tvCaseD;
    private TextView tvCau1, tvCau2, tvCau3, tvCau4, tvCau5, tvCau6, tvCau7, tvCau8, tvCau9, tvCau10, tvCau11, tvCau12, tvCau13, tvCau14, tvCau15;
    private ImageView ivFifty, ivAudience, ivRelative, ivChangeQuestion;
    private boolean isClickFifty, isClickAudience, isClickchangeQuestion, isClickRelative;
    private TextView tvTime;

    AnimListenerWhenSelectTrue listenSelectTrue;
    AnimListenerWhenSelectFalse listenSelectFalse;

    private String namePlayer;

    private MediaPlayer mediaPlayer;

    private boolean isSelect;
    private GameHelper gameHelper;
    private MyAnimation myAnim;
    private int time;
    private boolean stop;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addController();
        addEvent();
        countTime();
        newGame(gameHelper.getFirstQuestion());
        Intent mIntent = getIntent();
        namePlayer = mIntent.getStringExtra("name");
    }

    private void addController() {
        tvCaseA = (TextView) findViewById(R.id.tv_case_a);
        tvCaseB = (TextView) findViewById(R.id.tv_case_b);
        tvCaseC = (TextView) findViewById(R.id.tv_case_c);
        tvCaseD = (TextView) findViewById(R.id.tv_case_d);
        tvQuestion = (TextView) findViewById(R.id.tv_cau_hoi);

        rlCaseA = (RelativeLayout) findViewById(R.id.rl_case_a);
        rlCaseB = (RelativeLayout) findViewById(R.id.rl_case_b);
        rlCaseC = (RelativeLayout) findViewById(R.id.rl_case_c);
        rlCaseD = (RelativeLayout) findViewById(R.id.rl_case_d);

        tvTime = (TextView) findViewById(R.id.tv_time);

        tvCau1 = (TextView) findViewById(R.id.tv_cau1);
        tvCau2 = (TextView) findViewById(R.id.tv_cau2);
        tvCau3 = (TextView) findViewById(R.id.tv_cau3);
        tvCau4 = (TextView) findViewById(R.id.tv_cau4);
        tvCau5 = (TextView) findViewById(R.id.tv_cau5);
        tvCau6 = (TextView) findViewById(R.id.tv_cau6);
        tvCau7 = (TextView) findViewById(R.id.tv_cau7);
        tvCau8 = (TextView) findViewById(R.id.tv_cau8);
        tvCau9 = (TextView) findViewById(R.id.tv_cau9);
        tvCau10 = (TextView) findViewById(R.id.tv_cau10);
        tvCau11 = (TextView) findViewById(R.id.tv_cau11);
        tvCau12 = (TextView) findViewById(R.id.tv_cau12);
        tvCau13 = (TextView) findViewById(R.id.tv_cau13);
        tvCau14 = (TextView) findViewById(R.id.tv_cau14);
        tvCau15 = (TextView) findViewById(R.id.tv_cau15);

        ivFifty = (ImageView) findViewById(R.id.iv_nam_muoi);
        ivChangeQuestion = (ImageView) findViewById(R.id.iv_doi_cau_hoi);
        ivAudience = (ImageView) findViewById(R.id.iv_khan_gia);
        ivRelative = (ImageView) findViewById(R.id.iv_nguoi_than);

        gameHelper = new GameHelper(getApplicationContext());
        myAnim = new MyAnimation(getApplicationContext());
        listenSelectTrue = new AnimListenerWhenSelectTrue();
        listenSelectFalse = new AnimListenerWhenSelectFalse();

        time = START_TIME_COUNT;
    }

    public void newGame(Question questionFirst) {
        stop = false;
        time = START_TIME_COUNT;
        setColorForNewGame();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.hoi1);
        mediaPlayer.start();
        setQuestion(questionFirst);
    }

    private void addEvent() {

        // event click case
        selectCase(rlCaseA, CommonVLs.CASE_A);
        selectCase(rlCaseB, CommonVLs.CASE_B);
        selectCase(rlCaseC, CommonVLs.CASE_C);
        selectCase(rlCaseD, CommonVLs.CASE_D);

        ivFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickFifty && !isSelect) {
                    ivFifty.setImageResource(R.drawable.nammuoi2);
                    isClickFifty = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            createNewMedia(R.raw.nammuoi);
                            delay();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setQuestion(gameHelper.fiftyHelper());
                                }
                            });
                        }
                    }).start();
                }
            }
        });

        ivChangeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickchangeQuestion && !isSelect) {
                    ivChangeQuestion.setImageResource(R.drawable.doicauhoi2);
                    isClickchangeQuestion = true;
                    setQuestion(gameHelper.getNextQuestion());
                }
            }
        });

        ivAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickAudience && !isSelect) {
                    ivAudience.setImageResource(R.drawable.hoikhangia1);
                    isClickAudience = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int percentArr[] = gameHelper.audienceHelper();
                            final Intent intent = new Intent(MainActivity.this, AudienceHelpActivity.class);
                            intent.putExtra("percentArr", percentArr);
                            createNewMedia(R.raw.khangia);
                            delay();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(intent);
                                }
                            });
                        }
                    }).start();
                }
            }
        });

        ivRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickRelative && !isSelect) {
                    ivRelative.setImageResource(R.drawable.nguoithan2);
                    isClickRelative = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int caseRelative = gameHelper.relativeHelper();
                            final Intent mIntent = new Intent(MainActivity.this, RelativeActivity.class);
                            mIntent.putExtra("caseRelative", caseRelative);
                            createNewMedia(R.raw.goinguoithan);
                            delay();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(mIntent);
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }

    private void selectCase(final RelativeLayout rlCase, final int caseSelect) {
        rlCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Kiem tra xem da chon dap an chua
                if (!isSelect && !isSelect) {
                    // thay đổi nền đáp án đã chọn
                    rlCase.setBackgroundResource(R.drawable.select);
                    stop = true;
                    // thiết lập là đã có đáp án được chọn, không cho chọn thêm
                    isSelect = true;
                    // kiem tra dap an dung hay sai
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            switch (caseSelect) {
                                case CommonVLs.CASE_A:
                                    createNewMedia(R.raw.da);
                                    break;
                                case CommonVLs.CASE_B:
                                    createNewMedia(R.raw.db);
                                    break;
                                case CommonVLs.CASE_C:
                                    createNewMedia(R.raw.dc);
                                    break;
                                case CommonVLs.CASE_D:
                                    createNewMedia(R.raw.dd);
                                    break;
                            }
                            delay();
                            // phát nhạc đưa ra đáp án
                            if (new Random().nextInt(2) == 0) {
                                createNewMedia(R.raw.now1);
                            } else {
                                createNewMedia(R.raw.now2);
                            }
                            delay();

                            // NGười chơi chọn đúng đáp án
                            if (gameHelper.checkTrueCase(caseSelect)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        stop = true;
                                        // change image select true
                                        rlCase.setBackgroundResource(R.drawable.selecttrue);
                                        // animation fade in when select true
                                        myAnim.fadeIn(listenSelectTrue, rlCase);
                                    }
                                });
                                switch (caseSelect) {
                                    case CommonVLs.CASE_A:
                                        createNewMedia(R.raw.true_a);
                                        break;
                                    case CommonVLs.CASE_B:
                                        createNewMedia(R.raw.true_b);
                                        break;
                                    case CommonVLs.CASE_C:
                                        createNewMedia(R.raw.true_c);
                                        break;
                                    case CommonVLs.CASE_D:
                                        createNewMedia(R.raw.true_d);
                                        break;
                                }
                                delay();
                                if (gameHelper.getLevel() == 14) {
                                    createNewMedia(R.raw.pass_14);
                                }
                                if (gameHelper.getLevel() == 15) {
                                    createNewMedia(R.raw.pass_15);
                                }
                            }
                            // Người chơi chọn sai đáp án
                            else {
                                RelativeLayout rlCaseTrue = null;
                                switch (gameHelper.getTrueCase()) {
                                    case CommonVLs.CASE_A:
                                        rlCaseTrue = rlCaseA;
                                        break;
                                    case CommonVLs.CASE_B:
                                        rlCaseTrue = rlCaseB;
                                        break;
                                    case CommonVLs.CASE_C:
                                        rlCaseTrue = rlCaseC;
                                        break;
                                    case CommonVLs.CASE_D:
                                        rlCaseTrue = rlCaseD;
                                        break;
                                }
                                final RelativeLayout finalRlCaseTrue = rlCaseTrue;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        rlCase.setBackgroundResource(R.drawable.selectfalse);
                                        finalRlCaseTrue.setBackgroundResource(R.drawable.selecttrue);
                                        myAnim.fadeIn(listenSelectFalse, finalRlCaseTrue);
                                        myAnim.fadeIn(listenSelectFalse, rlCase);
                                    }
                                });
                                switch (gameHelper.getTrueCase()) {
                                    case CommonVLs.CASE_A:
                                        createNewMedia(R.raw.lose_a);
                                        break;
                                    case CommonVLs.CASE_B:
                                        createNewMedia(R.raw.lose_b);
                                        break;
                                    case CommonVLs.CASE_C:
                                        createNewMedia(R.raw.lose_c);
                                        break;
                                    case CommonVLs.CASE_D:
                                        createNewMedia(R.raw.lose_d);
                                        break;
                                }
                            }
                        }
                    }).start();
                }
            }
        });
    }

    private void delay() {
        try {
            Thread.sleep(mediaPlayer.getDuration() - DELAY_AUDIO_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class AnimListenerWhenSelectTrue implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            setColorLevel(gameHelper.getLevel());
            setColorDefaultForCase();
            setQuestion(gameHelper.getNextQuestion());

            setAudioNewQuestion();
            if (gameHelper.getLevel() == 5 || gameHelper.getLevel() == 10) {
                delay();
                createNewMedia(R.raw.quantrong);
            }
            time = START_TIME_COUNT;
            stop = false;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void setAudioNewQuestion() {
        switch (gameHelper.getLevel()) {
            case 1:
                createNewMedia(R.raw.hoi1);
                break;
            case 2:
                createNewMedia(R.raw.hoi2);
                break;
            case 3:
                createNewMedia(R.raw.hoi3);
                break;
            case 4:
                createNewMedia(R.raw.hoi4);
                break;
            case 5:
                createNewMedia(R.raw.hoi5);
                break;
            case 6:
                createNewMedia(R.raw.hoi6);
                break;
            case 7:
                createNewMedia(R.raw.hoi7);
                break;
            case 8:
                createNewMedia(R.raw.hoi8);
                break;
            case 9:
                createNewMedia(R.raw.hoi9);
                break;
            case 10:
                createNewMedia(R.raw.hoi10);
                break;
            case 11:
                createNewMedia(R.raw.hoi11);
                break;
            case 12:
                createNewMedia(R.raw.hoi12);
                break;
            case 13:
                createNewMedia(R.raw.hoi13);
                break;
            case 14:
                createNewMedia(R.raw.hoi14);
                break;
            case 15:
                createNewMedia(R.raw.hoi15);
                break;
        }
    }

    private void countTime() {
        time = START_TIME_COUNT;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (time > 0) {
                    try {
                        Thread.sleep(TIME_SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!stop) {
                        time--;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvTime.setText(time + "");
                        }
                    });
                }
                if (time <= 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            questionPlayContinueDialog();
                        }
                    });
                }
            }
        }).start();
    }

    private class AnimListenerWhenSelectFalse implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            questionPlayContinueDialog();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void createNewMedia(int idMedia) {
        mediaPlayer.release();
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), idMedia);
        mediaPlayer.start();
    }

    private void questionPlayContinueDialog() {
        stop = true;
        pref = getSharedPreferences("rank", MODE_PRIVATE);
        gameHelper.saveMark(pref);
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("Bạn có muốn chơi lại không???")
                .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (time <= 0) {
                            countTime();
                        } else time = START_TIME_COUNT;
                        newGame(gameHelper.getFirstQuestion());
                    }
                })
                .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();
        dialog.show();
    }

    private void setQuestion(Question question) {
        tvQuestion.setText(question.getQuestion());
        if (question.getCaseA().equals("")) {
            tvCaseA.setText("");
        } else {
            tvCaseA.setText("A. " + question.getCaseA());
        }
        if (question.getCaseB().equals("")) {
            tvCaseB.setText("");
        } else {
            tvCaseB.setText("B. " + question.getCaseB());
        }
        if (question.getCaseC().equals("")) {
            tvCaseC.setText("");
        } else {
            tvCaseC.setText("C. " + question.getCaseC());
        }
        if (question.getCaseD().equals("")) {
            tvCaseD.setText("");
        } else {
            tvCaseD.setText("D. " + question.getCaseD());
        }
    }

    public void setColorForNewGame() {
        setColorDefaultForCase();
        tvCau1.setBackgroundColor(Color.parseColor(backgroudLevelColor));
        tvCau2.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau3.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau4.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau5.setBackgroundColor(Color.parseColor(importantLevelColor));
        tvCau6.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau7.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau8.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau9.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau10.setBackgroundColor(Color.parseColor(importantLevelColor));
        tvCau11.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau12.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau13.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau14.setBackgroundColor(Color.parseColor(transparentLevelColor));
        tvCau15.setBackgroundColor(Color.parseColor(importantLevelColor));

        ivAudience.setImageResource(R.drawable.hoikhangia);
        ivChangeQuestion.setImageResource(R.drawable.doicauhoi);
        ivFifty.setImageResource(R.drawable.nammuoi);
        ivRelative.setImageResource(R.drawable.nguoithan);
        isClickAudience = false;
        isClickchangeQuestion = false;
        isClickFifty = false;
        isClickRelative = false;
    }

    private void setColorDefaultForCase() {
        rlCaseA.setBackgroundResource(R.drawable.traloicau);
        rlCaseB.setBackgroundResource(R.drawable.traloicau);
        rlCaseC.setBackgroundResource(R.drawable.traloicau);
        rlCaseD.setBackgroundResource(R.drawable.traloicau);
        isSelect = false;
    }

    private void setColorLevel(int level) {
        switch (level) {
            case 1:
                tvCau2.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau1.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 2:
                tvCau3.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau2.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 3:
                tvCau4.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau3.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 4:
                tvCau5.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau4.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 5:
                tvCau6.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau5.setBackgroundColor(Color.parseColor(importantLevelColor));
                break;
            case 6:
                tvCau7.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau6.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 7:
                tvCau8.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau7.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 8:
                tvCau9.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau8.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 9:
                tvCau10.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau9.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 10:
                tvCau11.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau10.setBackgroundColor(Color.parseColor(importantLevelColor));
                break;
            case 11:
                tvCau12.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau11.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 12:
                tvCau13.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau12.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 13:
                tvCau14.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau13.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
            case 14:
                tvCau15.setBackgroundColor(Color.parseColor(backgroudLevelColor));
                tvCau14.setBackgroundColor(Color.parseColor(transparentLevelColor));
                break;
        }
    }
}
