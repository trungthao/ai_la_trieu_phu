package com.uet.trungthao.ailatrieuphu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uet.trungthao.ailatrieuphu.Common.CommonVLs;
import com.uet.trungthao.ailatrieuphu.animation.MyAnimation;
import com.uet.trungthao.ailatrieuphu.model.Question;

/**
 * Created by JiH on 13/10/2016.
 */

public class MainActivity extends Activity {

    private static final long TIME_SLEEP = 1000;
    private static final int START_TIME_COUNT = 90;
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

    private boolean isSelect;
    private GameHelper gameHelper;
    private MyAnimation myAnim;
    private int time;
    private boolean stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addController();
        addEvent();
        countTime();
        newGame(gameHelper.getFirstQuestion());
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
        time = START_TIME_COUNT;
        setColorForNewGame();
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
                if (!isClickFifty) {
                    ivFifty.setImageResource(R.drawable.nammuoi2);
                    setQuestion(gameHelper.fiftyHelper());
                    isClickFifty = true;
                }
            }
        });

        ivChangeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickchangeQuestion) {
                    ivChangeQuestion.setImageResource(R.drawable.doicauhoi2);
                    setQuestion(gameHelper.getNextQuestion());
                    isClickchangeQuestion = true;
                }
            }
        });

        ivAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickAudience) {
                    isClickAudience = true;
                    ivAudience.setImageResource(R.drawable.hoikhangia1);
                    int percentArr[] = gameHelper.audienceHelper();
                    Intent intent = new Intent(MainActivity.this, AudienceHelpActivity.class);
                    intent.putExtra("percentArr", percentArr);
                    startActivity(intent);
                }
            }
        });

        ivRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickRelative) {
                    ivRelative.setImageResource(R.drawable.nguoithan2);
                    isClickRelative = true;
                    int caseRelative = gameHelper.relativeHelper();
                    Intent mIntent = new Intent(MainActivity.this, RelativeActivity.class);
                    mIntent.putExtra("caseRelative", caseRelative);
                    startActivity(mIntent);
                }
            }
        });
    }

    private void selectCase(final RelativeLayout rlCase, final int caseSelect) {
        rlCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiem tra xem da chon dap an chua
                if (!isSelect) {
                    // set background image when select
                    rlCase.setBackgroundResource(R.drawable.select);
                    // set seleted
                    isSelect = true;
                    // kiem tra dap an dung hay sai
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Tam dung de tao hoi hop
                            try {
                                Thread.sleep(TIME_SLEEP);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

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
                            } else {
                                // TODO: xu ly khi chon sai dap an
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
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
                                        rlCase.setBackgroundResource(R.drawable.selectfalse);
                                        rlCaseTrue.setBackgroundResource(R.drawable.selecttrue);
                                        myAnim.fadeIn(listenSelectFalse, rlCaseTrue);
                                        myAnim.fadeIn(listenSelectFalse, rlCase);
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });
    }

    private class AnimListenerWhenSelectTrue implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            setColorLevel(gameHelper.getLevel());
            setQuestion(gameHelper.getNextQuestion());
            time = START_TIME_COUNT;
            stop = false;
            setColorDefaultForCase();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void countTime() {
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

    private void questionPlayContinueDialog() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("Bạn có muốn chơi lại không???")
                .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newGame(gameHelper.getFirstQuestion());
                        countTime();
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
        tvCaseA.setText("A. " + question.getCaseA());
        tvCaseB.setText("B. " + question.getCaseB());
        tvCaseC.setText("C. " + question.getCaseC());
        tvCaseD.setText("D. " + question.getCaseD());
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
