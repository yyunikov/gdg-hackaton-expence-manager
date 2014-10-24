package ua.angrybeavers.material.em.ui.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ua.angrybeavers.material.em.R;

public class NewExpenseActivity extends Activity {

    private final String[] CATEGORIES = new String[] {
            "Automobile",
            "Entertainment",
            "Family",
            "Other"
    };

    private final String[] PAYMENT_METHODS = new String[] {
            "Cash",
            "Check",
            "Credit Card",
            "Debit",
            "Electronic Transfer"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Slide());
        getWindow().setExitTransition(new Slide());
        setContentView(R.layout.activity_new_expense);

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
        if(actionBar == null) {
            return;
        }

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.new_expense);
    }

    private void initView() {
        Spinner spinCategory = (Spinner) findViewById(R.id.spinCategory);
        Spinner spinPaymentMethod = (Spinner) findViewById(R.id.spinPaymentMethod);

        initSpinner(spinCategory, CATEGORIES);
        initSpinner(spinPaymentMethod, PAYMENT_METHODS);
    }

    private void initSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, items);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }
}
