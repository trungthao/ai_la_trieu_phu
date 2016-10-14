package com.uet.trungthao.ailatrieuphu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.uet.trungthao.ailatrieuphu.Common.CommonVLs;
import com.uet.trungthao.ailatrieuphu.db.DatabaseHelper;
import com.uet.trungthao.ailatrieuphu.model.Question;

import java.util.Random;

/**
 * Created by JiH on 12/10/2016.
 */

public class GameHelper {
    private Random random;
    private DatabaseHelper db;
    private Integer level;
    private Question question;

    public GameHelper(Context mContext) {
        db = new DatabaseHelper(mContext);
        random = new Random();
    }

    public Question getFirstQuestion() {
        level = 1;
        question = db.getQuestion(level.toString());
        return question;
    }

    public Question getNextQuestion() {
        if (level <= 14) {
            ++level;
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

    public int getLevel() {
        return level;
    }

    public Question fiftyHelper() {
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

    public int[] audienceHelper() {
        int percentArr[] = new int[4];
        int sum = 0;
        // -1 để vừa khớp với index của mảng percentArr[]
        int trueCase = question.getTrueCase() - 1;
        // tạo ngẫu nhiên 1 xác suất trả lời đúng
        int probabilityTrue = random.nextInt(100);
        //Xác suất trả lời đúng là 80%
        if (probabilityTrue < 80) {
            // tạo phần trăm cho câu trả lời đúng là > 50%
            percentArr[trueCase] = random.nextInt(50) + 50;
            sum = percentArr[trueCase];
            // tạo ngẫu nhiên giá trị cho các đáp án còn lại
            for (int i = 0; i < 4; i++) {
                if (trueCase != i) {
                    percentArr[i] = random.nextInt(101 - sum);
                    sum += percentArr[i];
                }
            }
            // hiện tại các cột có thể chưa đạt 100%, đoạn này xử lý việc đấy
            // Nếu đáp án đúng là 0 thì đáp án 1 sẽ là phần còn lại của các cột khác cho đủ 100
            if (trueCase == 0) {
                percentArr[1] = percentArr[0] + (100 - sum);
            } else {
                percentArr[trueCase-1] = percentArr[trueCase-1] + (100 - sum);
            }
        } else {
            percentArr[0] = random.nextInt(101);
            sum += percentArr[0];
            percentArr[1] = random.nextInt(101 - sum);
            sum += percentArr[1];
            percentArr[2] = random.nextInt(101 - sum);
            sum += percentArr[2];
            percentArr[3] = 100 - sum;
        }
        return percentArr;
    }

    public int relativeHelper() {
        // Xác suất để đưa ra câu trả lời đúng là 75%
        if (random.nextInt(100) <= 75) {
            return getTrueCase();
        } else {
            return random.nextInt(4)+1;
        }
    }
}
