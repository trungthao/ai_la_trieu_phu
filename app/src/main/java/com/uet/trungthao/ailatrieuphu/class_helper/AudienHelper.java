package com.uet.trungthao.ailatrieuphu.class_helper;

import com.uet.trungthao.ailatrieuphu.model.Question;

import java.util.Random;

/**
 * Created by JiH on 21/10/2016.
 */

public class AudienHelper {
    public int[] audienceHelper(Question question) {
        Random random = new Random();
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
            // hiện tại tổng các cột có thể chưa đạt 100%, đoạn này xử lý việc đấy
            // Nếu đáp án đúng là 0 thì đáp án 1 sẽ là phần còn lại của các cột khác cho đủ 100
            if (trueCase == 0) {
                percentArr[1] = percentArr[1] + (100 - sum);
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
}
