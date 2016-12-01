package com.uet.trungthao.ailatrieuphu.class_helper;

import android.content.Context;

import com.uet.trungthao.ailatrieuphu.db.DatabaseHelper;
import com.uet.trungthao.ailatrieuphu.model.Question;

/**
 * Created by JiH on 12/10/2016.
 */

public class QuestionHelper {
    private DatabaseHelper db;
    private Question question;

    public QuestionHelper(Context mContext) {
        db = new DatabaseHelper(mContext);
    }

    public Question newQuestion(Integer level) {
        if (level <= 15) {
            question = db.getQuestion(level.toString());
        }
        return question;
    }

    public boolean checkTrueCase(int chooseCase) {
        return question.getTrueCase() == chooseCase;
    }

    public int getTrueCase() {
        return question.getTrueCase();
    }

    public Question getQuestion() {
        return question;
    }
}
