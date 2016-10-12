package com.uet.trungthao.ailatrieuphu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uet.trungthao.ailatrieuphu.db.DatabaseHelper;
import com.uet.trungthao.ailatrieuphu.model.Question;

/**
 * Created by JiH on 12/10/2016.
 */

public class PlayFragment extends Fragment {

    private TextView tvCauHoi, tvCaseA, tvCaseB, tvCaseC, tvCaseD;
    private RelativeLayout rlCaseA, rlCaseB, rlCaseC, rlCaseD;
    private boolean isSelect;
    private View rootView;
    private Question question;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_playing, container, false);

        tvCaseA = (TextView) rootView.findViewById(R.id.tv_case_a);
        tvCaseB = (TextView) rootView.findViewById(R.id.tv_case_b);
        tvCaseC = (TextView) rootView.findViewById(R.id.tv_case_c);
        tvCaseD = (TextView) rootView.findViewById(R.id.tv_case_d);
        tvCauHoi = (TextView) rootView.findViewById(R.id.tv_cau_hoi);

        rlCaseA = (RelativeLayout) rootView.findViewById(R.id.rl_case_a);
        rlCaseB = (RelativeLayout) rootView.findViewById(R.id.rl_case_b);
        rlCaseC = (RelativeLayout) rootView.findViewById(R.id.rl_case_c);
        rlCaseD = (RelativeLayout) rootView.findViewById(R.id.rl_case_d);

        addEvent();

        return rootView;
    }

    private void addEvent() {
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

    private void checkCase(int caseSelect, RelativeLayout rlCase) {
        for (int i = 0; i < 999999999; i++) {

        }
        if (question.getTrueCase() == caseSelect) {
            rlCase.setBackgroundResource(R.drawable.selecttrue);
        } else {
            rlCase.setBackgroundResource(R.drawable.selectfalse);
        }
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
