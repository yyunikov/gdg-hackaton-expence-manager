package ua.angrybeavers.material.em.ui.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ua.angrybeavers.material.em.R;

/**
 * Created by vasylpirus on 24.10.14.
 */
public class ExpenseDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.expense_details_layout);


        setupActionBar();


        Button _buttonSummary = (Button) findViewById(R.id.personal_expense_details_summary_btn);
        _buttonSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button _buttonBudget = (Button) findViewById(R.id.personal_expense_details_budget_btn);
        _buttonSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void setupActionBar() {
        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.app_name);
        }
    }




}
