package ua.angrybeavers.material.em.ui.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import ua.angrybeavers.material.em.R;
import ua.angrybeavers.material.em.ui.fragments.dialogs.NewExpenseGroupDialog;

/**
 * Created by Parazit on 24.10.2014.
 */
public class NewExpensesTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_espenses_test);

        initView();
    }

    private void initView() {
        findViewById(R.id.btnClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnClickClicked();
            }
        });
    }

    private void onBtnClickClicked() {
        FragmentTransaction fTransaction = getFragmentManager().beginTransaction();
        fTransaction.add(new NewExpenseGroupDialog(), NewExpenseGroupDialog.TAG);
        fTransaction.commitAllowingStateLoss();
    }
}
