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
package ua.angrybeavers.material.em.ui.items;


import ua.angrybeavers.material.em.R;


/**
 * @author yyunikov
 */
public class DrawerItemPrimary extends DrawerItem {

    public final String mTitle;

    public DrawerItemPrimary(final String title) {
        mTitle = title;
    }

    @Override
    public int getLayout() {
        return R.layout.item_drawer_primary;
    }
}
