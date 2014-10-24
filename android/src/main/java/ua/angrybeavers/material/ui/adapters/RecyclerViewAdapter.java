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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ua.angrybeavers.material.R;
import ua.angrybeavers.material.ui.items.RecyclerViewItem;

import java.util.List;

/**
 * @author yyunikov
 */
public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<RecyclerViewItem> mDataset;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleView;

        public TextView mSubTitleView;

        public ViewHolder(View v) {
            super(v);

            mTitleView = (TextView) v.findViewById(android.R.id.text1);
            mSubTitleView = (TextView) v.findViewById(android.R.id.text2);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            // dummy onClick
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(final List<RecyclerViewItem> dataset) {
        mDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_two_line, parent, false);

        // set the view's size, margins, paddings and layout parameters
        // ...

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitleView.setText(mDataset.get(position).getTitle());
        holder.mSubTitleView.setText(mDataset.get(position).getSubTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}