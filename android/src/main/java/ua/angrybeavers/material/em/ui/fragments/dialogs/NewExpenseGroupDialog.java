package ua.angrybeavers.material.em.ui.fragments.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ua.angrybeavers.material.em.R;

/**
 * Created by vyatsykiv on 24.10.2014.
 */
public class NewExpenseGroupDialog extends DialogFragment {

    public static final String TAG = NewExpenseGroupDialog.class.getSimpleName();

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.new_expenses_group);

        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "add new account");
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "cancel adding new account");
            }
        });

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_expenses_group, null);

        Spinner spinCurrency = (Spinner) view.findViewById(R.id.spinCurrency);
        Spinner spinAutoAndMileage = (Spinner) view.findViewById(R.id.spinAutoAndMileage);

        initSpinner(spinCurrency, CURRENCIES);
        initSpinner(spinAutoAndMileage, AUTO_AND_MILEAGE_ITEMS);

        builder.setView(view);

        return builder.create();
    }

    private void initSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }
}

