package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.turingtechnologies.materialscrollbar.CustomIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;
import com.turingtechnologies.materialscrollbar.FastScrollerUtil;

/**
 * @author hendrawd on 6/16/17
 */

public class GridViewWithHeaderActivity extends AppCompatActivity {

    private static final int DATASET_COUNT = 10000;
    private static final int SPAN_COUNT = 3;
    private RecyclerView mRecyclerView;
    private GridViewWithHeaderAdapter mAdapter;
    private DragScrollBar mDragScrollBar;
    protected GridViewWithHeaderItem[] mDataset;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_with_header);

        initDataSet();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mDataset[position].isHeader() ? SPAN_COUNT : 1;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mDragScrollBar = (DragScrollBar) findViewById(R.id.dragScrollBar);
        mDragScrollBar.setIndicator(new CustomIndicator(this), true);

        mAdapter = new GridViewWithHeaderAdapter(mDataset);
        final int headerHeight = (int) Utils.dp2px(this, 24F);
        final int contentHeight = (int) Utils.dp2px(this, 72F);
        mAdapter.setHeaderHeight(headerHeight);
        mAdapter.setRowHeight(contentHeight);
        mRecyclerView.setAdapter(mAdapter);
        FastScrollerUtil.initHeaderScroller(mRecyclerView);
    }

    private void initDataSet() {
        mDataset = new GridViewWithHeaderItem[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = new GridViewWithHeaderItem();
            mDataset[i].setText("This is element #" + i);
            if (i % 8 == 0) {
                mDataset[i].setHeader(true);
                mDataset[i].setText("This is element #" + i + " - act as header");
            } else {
                mDataset[i].setText("This is element #" + i + " - act as content");
            }
        }
    }
}
