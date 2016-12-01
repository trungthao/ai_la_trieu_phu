package com.uet.trungthao.ailatrieuphu.class_helper;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by JiH on 21/10/2016.
 */

public class SaveMark {
    private String TAG = SaveMark.class.getSimpleName();

    public void saveMark(SharedPreferences pref, GameHelper gameHelper) {
        if (pref.getInt("rank1", 0) < gameHelper.getLevel()) {
            int arrRank[] = new int[6];
            for (int i = 1; i <= 5; i++) {
                arrRank[i] = pref.getInt("rank" + i, 0);
            }
            Log.i(TAG, arrRank[0] + arrRank[1] + arrRank[2] + arrRank[3] + arrRank[4] + "");
            Arrays.sort(arrRank);
            arrRank[1] = gameHelper.getLevel()-1;
            Arrays.sort(arrRank);

            SharedPreferences.Editor editor = pref.edit();
            for (int i = 1; i <= 5; i++) {
                editor.putInt("rank" + i, arrRank[i]);
            }
            editor.commit();
        }
    }
}