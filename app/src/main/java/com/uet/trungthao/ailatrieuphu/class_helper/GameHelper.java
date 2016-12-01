package com.uet.trungthao.ailatrieuphu.class_helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.uet.trungthao.ailatrieuphu.model.Question;

/**
 * Created by JiH on 21/10/2016.
 */

public class GameHelper {
    private FiftyHelper fiftyHelper;
    private QuestionHelper questionHelper;
    private RelativeHelper relativeHelper;
    private AudienHelper audienHelper;
    private SaveMark saveMark;
    private Integer level;

    public GameHelper(Context context) {
        fiftyHelper = new FiftyHelper();
        questionHelper = new QuestionHelper(context);
        relativeHelper = new RelativeHelper();
        audienHelper = new AudienHelper();
        saveMark = new SaveMark();
        level = 1;
    }


    public Question fiftyHelper() {
        return fiftyHelper.moveTwoCaseFalse(questionHelper.getQuestion());
    }


    public Question getNextQuestion() {
        return questionHelper.newQuestion(++level);
    }

    public int[] audienceHelper() {
        return audienHelper.audienceHelper(questionHelper.getQuestion());
    }

    public int relativeHelper() {
        return relativeHelper.relativeHelper(questionHelper.getQuestion());
    }

    public boolean checkTrueCase(int caseSelect) {
        return questionHelper.getTrueCase() == caseSelect;
    }

    public Integer getLevel() {
        return level;
    }

    public int getTrueCase() {
        return questionHelper.getTrueCase();
    }

    public Question getFirstQuestion() {
        return questionHelper.newQuestion(1);
    }

    public void saveMark(SharedPreferences pref) {
        saveMark.saveMark(pref, this);
    }
}
