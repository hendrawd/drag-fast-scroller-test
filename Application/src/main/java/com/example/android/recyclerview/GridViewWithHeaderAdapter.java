/*
* Copyright (C) 2014 The Android Open Source Project
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

package com.example.android.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.common.logger.Log;
import com.turingtechnologies.materialscrollbar.FastScrollerUtil;
import com.turingtechnologies.materialscrollbar.ICustomAdapter;
import com.turingtechnologies.materialscrollbar.ICustomScroller;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class GridViewWithHeaderAdapter extends RecyclerView.Adapter<GridViewWithHeaderAdapter.ViewHolder>
        implements ICustomAdapter, FastScrollerUtil.IHeaderAdapter, ICustomScroller {
    private static final String TAG = "CustomAdapter";

    private GridViewWithHeaderItem[] mDataSet;
    private FastScrollerUtil.HeaderScrollManager manager;
    private int headerHeight;
    private int rowHeight;

    @Override
    public boolean isHeader(int index) {
        return mDataSet[index].isHeader();
    }

    @Override
    public void initScrollManager(int span) {
        manager = new FastScrollerUtil.HeaderScrollManager(span);
        manager.calcData(this);
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    @Override
    public int getHeaderHeight() {
        return headerHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    @Override
    public int getRowHeight() {
        return rowHeight;
    }

    @Override
    public int getDepthForItem(int index) {
        return manager.getDepthForItem(index);
    }

    @Override
    public int getTotalDepth() {
        return manager.getTotalDepth();
    }

    @Override
    public int getItemIndexForScroll(float scrollBarPos) {
        return manager.getItemIndexForScroll(scrollBarPos);
    }

    @Override
    public String getCustomStringForElement(int element) {
        return mDataSet[element].getText();
    }

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public GridViewWithHeaderAdapter(GridViewWithHeaderItem[] dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataSet[position].isHeader() ? 0 : 1;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v;
        if (viewType == 0) {
            v = inflater.inflate(R.layout.header_item, viewGroup, false);
        } else {
            v = inflater.inflate(R.layout.content_item, viewGroup, false);
        }

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataSet[position].getText());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
