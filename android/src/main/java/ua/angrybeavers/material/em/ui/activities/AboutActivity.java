/*
 * Copyright 2014 Yuriy Yunikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ua.angrybeavers.material.em.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;

import ua.angrybeavers.material.em.R;

/**
 * @author yyunikov
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        setupActionBar();

        setupDrawer();

        startRevealAnimation();
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        final ActionBar actionBar = getActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.activity_about);
        }
    }

    private void startRevealAnimation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        View view = findViewById(R.id.holderAboutInfo);
                        startShowAnimation(view);
                    }
                });
            }
        }, 300);
    }

    private void startShowAnimation(final View view) {
        DisplayMetrics dMetrics = getResources().getDisplayMetrics();
        int screenHeight = dMetrics.heightPixels;
        int screenWidth = dMetrics.widthPixels;

        int cx = screenWidth / 2;
        int cy = screenHeight / 2;

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, screenHeight);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                view.setVisibility(View.VISIBLE);
            }
        });
        anim.setDuration(700);
        anim.start();
    }

    private void startHideAnimation(final View view) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        int initialRadius = view.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

        anim.start();
    }
}
