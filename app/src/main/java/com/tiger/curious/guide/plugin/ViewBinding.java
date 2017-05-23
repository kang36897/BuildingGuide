package com.tiger.curious.guide.plugin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.databinding.BindingAdapter;
import android.view.View;

import com.tiger.curious.guide.R;

/**
 * Created by bkang016 on 5/20/17.
 */

public class ViewBinding {


    @BindingAdapter("animateVisibility")
    public static void animateVisibility(final View targetView, final int visibility) {
        Integer endAnimVisibility = (Integer) targetView.getTag(R.id.finalVisibility);

        int oldVisibility = endAnimVisibility == null ? targetView.getVisibility() : endAnimVisibility;

        if (oldVisibility == visibility) {
            return;
        }

        boolean isVisible = oldVisibility == View.VISIBLE;
        boolean willBeVisible = visibility == View.VISIBLE;

        targetView.setVisibility(View.VISIBLE);

        float startAlpha = isVisible ? 1f : 0f;
        if (endAnimVisibility != null) {
            startAlpha = targetView.getAlpha();
        }

        float endAlpha = willBeVisible ? 1f : 0f;

        ObjectAnimator alpha = ObjectAnimator.ofFloat(targetView, View.ALPHA, startAlpha, endAlpha);
        alpha.setAutoCancel(true);

        alpha.addListener(new AnimatorListenerAdapter() {
            private boolean isCanceled;

            @Override
            public void onAnimationCancel(Animator animation) {
                isCanceled = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                targetView.setTag(R.id.finalVisibility, null);

                if (!isCanceled) {
                    targetView.setAlpha(1f);
                    targetView.setVisibility(visibility);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                targetView.setTag(R.id.finalVisibility, visibility);
            }
        });

        alpha.start();
    }
}
