package ua.angrybeavers.material.em.ui.fragments.dialogs;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import ua.angrybeavers.material.em.R;

/**
 * Created by vyatsykiv on 24.10.2014.
 */
public class NewExpenseGroupDialog extends DialogFragment {

    public static final String TAG = NewExpenseGroupDialog.class.getSimpleName();

    public OnAddClickListener mOnAddClickListener;

    private final String[] CURRENCIES = new String[] {
            "Dollar",
            "Euro"
    };

    private final String[] AUTO_AND_MILEAGE_ITEMS = new String[] {
            "Miles (mi)",
            "Kilometers (km)"
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.NewExpensesGroupTheme);
        final View view = initView();

        builder.setTitle(R.string.new_expenses_group);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String name = ((EditText) view.findViewById(R.id.editName)).getText().toString();
                final String description = ((EditText) view.findViewById(R.id.editName)).getText().toString();

                if (name != null && description != null) {
                    mOnAddClickListener.onAddClick(name, description);
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "cancel adding new account");
            }
        });

        builder.setView(view);

        final Dialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        return dialog;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof OnAddClickListener)) {
            throw new IllegalArgumentException("Activity must implement OnAddClickListener");
        }
        mOnAddClickListener = (OnAddClickListener) activity;
    }

    private View initView() {
        DisplayMetrics dMetrics = getResources().getDisplayMetrics();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_expenses_group, null);
        view.setMinimumWidth((int)(dMetrics.widthPixels * 0.8f));

        Spinner spinCurrency = (Spinner) view.findViewById(R.id.spinCurrency);
        Spinner spinAutoAndMileage = (Spinner) view.findViewById(R.id.spinAutoAndMileage);
        initSpinner(spinCurrency, CURRENCIES);
        initSpinner(spinAutoAndMileage, AUTO_AND_MILEAGE_ITEMS);

        return view;
    }

    private void initSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    public static interface OnAddClickListener {

        public void onAddClick(final String name, final String description);
    }
}

