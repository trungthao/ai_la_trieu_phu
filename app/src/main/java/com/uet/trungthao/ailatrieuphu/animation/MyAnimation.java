package com.uet.trungthao.ailatrieuphu.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.uet.trungthao.ailatrieuphu.R;

/**
 * Created by JiH on 12/10/2016.
 */

public class MyAnimation {
    private Animation animMove1, animMove2, animFadeIn;

    public MyAnimation(Context context) {
        animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        animMove1 = AnimationUtils.loadAnimation(context, R.anim.move1);
        animMove2 = AnimationUtils.loadAnimation(context, R.anim.move2);
    }

    public void move(View v1, View v2, Animation.AnimationListener listener) {
        v1.startAnimation(animMove1);
        animMove1.setAnimationListener(listener);
        v2.startAnimation(animMove2);
    }

    public void fadeIn(Animation.AnimationListener listener, View... views) {
        animFadeIn.setAnimationListener(listener);
        for (int i = 0; i < views.length; i++) {
            views[i].startAnimation(animFadeIn);
        }
    }
}
