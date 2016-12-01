package com.uet.trungthao.ailatrieuphu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.uet.trungthao.ailatrieuphu.animation.MyAnimation;

public class SlashActivity extends AppCompatActivity implements Animation.AnimationListener {

    private static final String TAG = SlashActivity.class.getSimpleName();
    private static final String NAME_PLAYER = "name";
    RelativeLayout rlFirstScreen, rlSecondScreen;
    private ImageView ivNewGame, ivHighScore;
    private MyAnimation myAnim;
    private boolean isFirstPlay = true;
    private SharedPreferences pref;
    private String namePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        addController();
        addEvent();
        showDialogName();
    }

    private void showDialogName() {
        LayoutInflater lf = LayoutInflater.from(getApplicationContext());
        View view = lf.inflate(R.layout.dialog_enter_name, null);
        final EditText edtName = (EditText) view.findViewById(R.id.edt_name);
        namePlayer = pref.getString("name", "");
        Log.i(TAG, namePlayer);
        edtName.setText(namePlayer);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = pref.edit();
                        namePlayer = edtName.getText().toString();
                        editor.putString("name", namePlayer);
                        editor.commit();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent mIntent = new Intent(SlashActivity.this, MainActivity.class);
        startActivity(mIntent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
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

    private void addController() {
        ivNewGame = (ImageView) findViewById(R.id.iv_new_game);
        ivHighScore = (ImageView) findViewById(R.id.iv_high_score);
        rlFirstScreen = (RelativeLayout) findViewById(R.id.rl_first_screen);
        rlSecondScreen = (RelativeLayout) findViewById(R.id.rl_second_screen);
        ivHighScore = (ImageView) findViewById(R.id.iv_high_score);

        myAnim = new MyAnimation(getApplicationContext());
        pref = getSharedPreferences(NAME_PLAYER, MODE_PRIVATE);
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

        ivHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SlashActivity.this, RankActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
