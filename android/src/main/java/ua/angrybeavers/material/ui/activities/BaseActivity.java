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
package ua.angrybeavers.material.ui.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import ua.angrybeavers.material.R;
import ua.angrybeavers.material.ui.adapters.NavigationDrawerAdapter;
import ua.angrybeavers.material.ui.drawer.ActionBarDrawerToggle;
import ua.angrybeavers.material.ui.drawer.DrawerArrowDrawable;
import ua.angrybeavers.material.ui.items.DrawerItemDivider;
import ua.angrybeavers.material.ui.items.DrawerItemPrimary;

/**
 * @author yyunikov
 */
public abstract class BaseActivity extends Activity {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }

        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    protected void setupActionBar() {
        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.app_name);
        }
    }

    protected void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        setDrawerAdapter();

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                createDrawerArrow(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                /* empty */
            }


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                /* empty */
            }
        };

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private DrawerArrowDrawable createDrawerArrow() {
        return new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
    }

    private void setDrawerAdapter() {
        final NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(this);

        adapter.add(new DrawerItemPrimary("RecyclerView and Floating Button"));
        adapter.add(new DrawerItemDivider());
        adapter.add(new DrawerItemPrimary("Activity Transition"));
        adapter.add(new DrawerItemPrimary("View Stage Transition"));
        adapter.add(new DrawerItemPrimary("Reveal Effect"));

        mDrawerList.setAdapter(adapter);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            final Intent intent;

            switch (position) {
                case 0:
                    intent = new Intent(BaseActivity.this, RecyclerViewActivity.class).
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_SINGLE_TOP); break;
                case 2:
                    intent = new Intent(BaseActivity.this, ActivityTransition.class).
                            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); break;
                case 3:
                    intent = null;
                    break;
                case 4:
                    intent = null;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid position specified!");
            }

            mDrawerLayout.closeDrawer(mDrawerList);

            if (intent != null) {
                startActivity(intent);
            }
        }
    }
}
