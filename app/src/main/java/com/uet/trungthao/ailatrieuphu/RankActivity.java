package com.uet.trungthao.ailatrieuphu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by JiH on 21/10/2016.
 */
public class RankActivity extends Activity {

    private TextView tvTop1, tvTop2, tvTop3, tvTop4, tvTop5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        addController();
        setMark();
    }

    private void addController() {
        tvTop1 = (TextView) findViewById(R.id.tv_top_1);
        tvTop2 = (TextView) findViewById(R.id.tv_top_2);
        tvTop3 = (TextView) findViewById(R.id.tv_top_3);
        tvTop4 = (TextView) findViewById(R.id.tv_top_4);
        tvTop5 = (TextView) findViewById(R.id.tv_top_5);
    }

    private void setMark() {
        SharedPreferences pref = getSharedPreferences("rank", MODE_PRIVATE);
        int levelTemp;
        String name = getSharedPreferences("name", MODE_PRIVATE).getString("name","");

        levelTemp = pref.getInt("rank1", 0);
        if (levelTemp != 0) {
            tvTop1.setText(name + " : " + getMark(levelTemp));
        }

        levelTemp = pref.getInt("rank2", 0);
        if (levelTemp != 0) {
            tvTop2.setText(name + " : " + getMark(levelTemp));
        }

        levelTemp = pref.getInt("rank3", 0);
        if (levelTemp != 0) {
            tvTop3.setText(name + " : " + getMark(levelTemp));
        }

        levelTemp = pref.getInt("rank4", 0);
        if (levelTemp != 0) {
            tvTop4.setText(name + " : " + getMark(levelTemp));
        }

        levelTemp = pref.getInt("rank5", 0);
        if (levelTemp != 0) {
            tvTop5.setText(name + " : " + getMark(levelTemp));
        }
    }

    private String getMark(int level) {
        String [] arrMarkLevel = getResources().getStringArray(R.array.mark_level);
        return arrMarkLevel[level-1];
    }
}
