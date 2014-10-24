package ua.angrybeavers.material.em.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;

import ua.angrybeavers.material.em.R;

/**
 * Created by Volodymyr on 24.10.2014.
 */
public class RevealEffectActivity extends Activity {

    private boolean mIsHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_effect);

        initActionBar();
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setTitle("Reveal animation");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void initView() {
        findViewById(R.id.btnStartRevealAnim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsHidden) {
                    startShowAnimation();
                    mIsHidden = false;
                } else {
                    startHideAnimation();
                    mIsHidden = true;
                }
            }
        });
    }

    private void startShowAnimation() {
        final View myView = findViewById(R.id.imgAndroid);

        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        int finalRadius = myView.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                myView.setVisibility(View.VISIBLE);
            }
        });
        anim.start();
    }

    private void startHideAnimation() {
        final View myView = findViewById(R.id.imgAndroid);

        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        int initialRadius = myView.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

        anim.start();
    }
}
