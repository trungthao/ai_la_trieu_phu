package com.uet.trungthao.ailatrieuphu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JiH on 14/10/2016.
 */

public class ShowCaseRelativeActivity extends Activity{

    private ImageView ivContinue;
    private TextView tvShowCaseRelative;

    private String nameRelative;
    private int caseRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_case_relative);
        Intent mIntent = getIntent();
        nameRelative = mIntent.getStringExtra("nameRelative");
        caseRelative = mIntent.getIntExtra("caseRelative", 0);

        addController();
        addEvent();

        setMessenger();
    }

    private void setMessenger() {
        char caseRelativeChar = 0;
        switch (caseRelative) {
            case 1: caseRelativeChar = 'A';
                break;
            case 2: caseRelativeChar = 'B';
                break;
            case 3: caseRelativeChar = 'C';
                break;
            case 4: caseRelativeChar = 'D';
                break;
        }
        tvShowCaseRelative.setText("Theo " + nameRelative + " thì hình như là đáp án: " + caseRelativeChar + "\nSai tự chịu nhá... pp");
    }

    private void addController() {
        ivContinue = (ImageView) findViewById(R.id.iv_continue);
        tvShowCaseRelative = (TextView) findViewById(R.id.tv_show_case_relative);
    }

    private void addEvent() {
        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
