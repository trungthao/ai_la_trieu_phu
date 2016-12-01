package com.uet.trungthao.ailatrieuphu.class_helper;

import com.uet.trungthao.ailatrieuphu.model.Question;

import java.util.Random;

/**
 * Created by JiH on 21/10/2016.
 */

public class RelativeHelper {
    public int relativeHelper(Question question) {
        Random random = new Random();
        // Xác suất để đưa ra câu trả lời đúng là 75%
        if (random.nextInt(100) <= 75) {
            return question.getTrueCase();
        } else {
            return random.nextInt(4)+1;
        }
    }
}
