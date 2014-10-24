package ua.angrybeavers.material.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ua.angrybeavers.material.R;

public class ActivityTransition extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        setupActionBar();
        setupDrawer();

        initialize();
    }

    private void initialize() {
        final Button nextActivityButton = (Button) findViewById(R.id.activity_transition_button);
        nextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextActivity();
            }
        });
    }

    private void showNextActivity() {
        final Intent intent = new Intent(this, ActivityTransitionStage.class).
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
