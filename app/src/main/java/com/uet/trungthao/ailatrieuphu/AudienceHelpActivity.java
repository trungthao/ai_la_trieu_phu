package com.uet.trungthao.ailatrieuphu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JiH on 13/10/2016.
 */

public class AudienceHelpActivity extends Activity {

    private TextView tvColumnA, tvColumnB, tvColumnC, tvColumnD;
    private ImageView ivContinue;
    private TextView tvPercentA, tvPercentB, tvPercentC, tvPercentD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audience_helper);

        addController();
        addEvents();

        Intent mIntent = getIntent();
        int [] percentArr = mIntent.getIntArrayExtra("percentArr");
        setHeightColumn(percentArr[0], percentArr[1], percentArr[2], percentArr[3]);
    }

    private void addEvents() {
        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addController() {
        tvColumnA = (TextView) findViewById(R.id.tv_column_a);
        tvColumnB = (TextView) findViewById(R.id.tv_column_b);
        tvColumnC = (TextView) findViewById(R.id.tv_column_c);
        tvColumnD = (TextView) findViewById(R.id.tv_column_d);
        ivContinue = (ImageView) findViewById(R.id.iv_continue);

        tvPercentA = (TextView) findViewById(R.id.tv_percent_a);
        tvPercentB = (TextView) findViewById(R.id.tv_percent_b);
        tvPercentC = (TextView) findViewById(R.id.tv_percent_c);
        tvPercentD = (TextView) findViewById(R.id.tv_percent_d);
    }

    public void setHeightColumn(int heightColumnA, int heightColumnB, int heightColumnC, int heightColumnD) {
        ViewGroup.LayoutParams lp;

        lp = tvColumnA.getLayoutParams();
        lp.height = heightColumnA * 5;

        lp = tvColumnB.getLayoutParams();
        lp.height = heightColumnB * 5;

        lp = tvColumnC.getLayoutParams();
        lp.height = heightColumnC * 5;

        lp = tvColumnD.getLayoutParams();
        lp.height = heightColumnD * 5;

        tvColumnA.requestLayout();
        tvColumnB.requestLayout();
        tvColumnC.requestLayout();
        tvColumnD.requestLayout();

        tvPercentA.setText(heightColumnA + "%");
        tvPercentB.setText(heightColumnB + "%");
        tvPercentC.setText(heightColumnC + "%");
        tvPercentD.setText(heightColumnD + "%");
    }
}
