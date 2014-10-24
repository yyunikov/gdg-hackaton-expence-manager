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
import android.graphics.Outline;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import ua.angrybeavers.material.em.R;
import ua.angrybeavers.material.em.ui.adapters.RecyclerViewAdapter;
import ua.angrybeavers.material.em.ui.items.RecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends BaseActivity {

    private static final int DATASET_SIZE = 1000;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        setupActionBar();

        setupDrawer();

        setupRecyclerView();
        setupAddButton();
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

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

        final View addButton = findViewById(R.id.add_button);
        addButton.setOutline(outline);
        addButton.setClipToOutline(true);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

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

    private class InitializingTask extends AsyncTask<Void, Void, List<RecyclerViewItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setProgressBarVisible(true);
        }

        @Override
        protected List<RecyclerViewItem> doInBackground(final Void... voids) {
            // intentionally sleeping to show progress bar
            try {
                Thread.sleep(3000);
            } catch (final InterruptedException e) {
                Log.e(AccountActivity.class.getName(), "Thread was interrupted!");
            }

            return createDataSet();
        }

        @Override
        protected void onPostExecute(final List<RecyclerViewItem> aVoid) {
            super.onPostExecute(aVoid);

            final RecyclerView.Adapter adapter = new RecyclerViewAdapter(aVoid);
            recyclerView.setAdapter(adapter);
            setProgressBarVisible(false);
        }

        private List<RecyclerViewItem> createDataSet() {
            final List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();

            recyclerViewItems.add(new RecyclerViewItem("Personal Expense", "Current Balance: "));

            return recyclerViewItems;
        }
    }
}
