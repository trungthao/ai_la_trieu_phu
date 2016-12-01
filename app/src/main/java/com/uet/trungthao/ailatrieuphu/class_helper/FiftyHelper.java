package com.uet.trungthao.ailatrieuphu.class_helper;

import com.uet.trungthao.ailatrieuphu.common.CommonVLs;
import com.uet.trungthao.ailatrieuphu.model.Question;

import java.util.Random;

/**
 * Created by JiH on 21/10/2016.
 */

public class FiftyHelper {
    public Question moveTwoCaseFalse(Question question) {
        Random random = new Random();
        int i1, i2;
        do {
            i1 = random.nextInt(4)+1;
        } while (i1 == question.getTrueCase());

        do {
            i2 = random.nextInt(4)+1;
        } while (i2 == question.getTrueCase() || i1 == i2);

        if (i1 == CommonVLs.CASE_A || i2 == CommonVLs.CASE_A) {
            question.setCaseA("");
        }
        if (i1 == CommonVLs.CASE_B || i2 == CommonVLs.CASE_B) {
            question.setCaseB("");
        }
        if (i1 == CommonVLs.CASE_C || i2 == CommonVLs.CASE_C) {
            question.setCaseC("");
        }
        if (i1 == CommonVLs.CASE_D || i2 == CommonVLs.CASE_D) {
            question.setCaseD("");
        }
        return question;
    }
}
