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

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Outline;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import ua.angrybeavers.material.em.R;
import ua.angrybeavers.material.em.storage.Storage;
import ua.angrybeavers.material.em.ui.adapters.RecyclerViewAdapter;
import ua.angrybeavers.material.em.ui.fragments.dialogs.NewExpenseGroupDialog;
import ua.angrybeavers.material.em.ui.items.DividerItemDecoration;
import ua.angrybeavers.material.em.ui.items.RecyclerViewItem;

public class AccountActivity extends BaseActivity implements NewExpenseGroupDialog.OnAddClickListener {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private View addButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);
        setupActionBar();

        setupDrawer();

        setupAddButton();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        new InitializingTask().execute();
    }

    private void setProgressBarVisible(final boolean visible) {
        final ProgressBar progressBar = ((ProgressBar) findViewById(R.id.progress));

        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void setupAddButton() {
        final int diameter = getResources().getDimensionPixelSize(R.dimen.round_button_diameter);
        Outline outline = new Outline();
        outline.setOval(0, 0, diameter, diameter);

        addButton = findViewById(R.id.add_button);
        addButton.setOutline(outline);
        addButton.setClipToOutline(true);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                FragmentTransaction fTransaction = getFragmentManager().beginTransaction();
                fTransaction.add(new NewExpenseGroupDialog(), NewExpenseGroupDialog.TAG);
                fTransaction.commitAllowingStateLoss();
            }
        });
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        final ActionBar actionBar = getActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.activity_account);
        }
    }

    @Override
    public void onAddClick(final String name, final String description) {
        Storage.getInstance().getListItems().add(0, new RecyclerViewItem(name, description));

        adapter.notifyItemInserted(0);
    }

    private class InitializingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setProgressBarVisible(true);
            addButton.setEnabled(false);
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            // intentionally sleeping to show progress bar
            try {
                Thread.sleep(2000);
            } catch (final InterruptedException e) {
                Log.e(AccountActivity.class.getName(), "Thread was interrupted!");
            }

            Storage.getInstance().getListItems();

            return null;
        }

        @Override
        protected void onPostExecute(final Void aVoid) {
            super.onPostExecute(aVoid);

            adapter = new RecyclerViewAdapter(Storage.getInstance().getListItems(), AccountActivity.this);
            recyclerView.setAdapter(adapter);
            setProgressBarVisible(false);
            addButton.setEnabled(true);
        }
    }
}
