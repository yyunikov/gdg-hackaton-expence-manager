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
package ua.angrybeavers.material.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ua.angrybeavers.material.R;
import ua.angrybeavers.material.ui.items.DrawerItem;
import ua.angrybeavers.material.ui.items.DrawerItemPrimary;

/**
 * @author yyunikov
 */
public class NavigationDrawerAdapter extends ArrayAdapter<DrawerItem> {

    public NavigationDrawerAdapter(final Context context) {
        super(context, 0);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DrawerItem item = getItem(position);
        final ViewHolder holder;

        if (item instanceof DrawerItemPrimary) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(item.getLayout(), parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.mTitle.setText(((DrawerItemPrimary) item).mTitle);
        } else if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(item.getLayout(), parent, false);
        }

        return convertView;
    }

    @Override
    public DrawerItem getItem(int position) {
        return super.getItem(position);
    }

    static class ViewHolder {

        TextView mTitle;

        public ViewHolder(final View view) {
            mTitle = (TextView) view.findViewById(R.id.drawer_item_title);
        }
    }
}