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
import android.os.Bundle;
import ua.angrybeavers.material.em.R;

/**
 * @author yyunikov
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        setupActionBar();

        setupDrawer();
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        final ActionBar actionBar = getActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.activity_about);
        }
    }
}
