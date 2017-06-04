package com.tiger.curious.guide.view;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by bkang016 on 5/23/17.
 */

public class AlphabetKeyboardLayoutManager extends RecyclerView.LayoutManager {


    private SparseArray<View> mCachedViews;
    private int mRowNum = 3;
    private int[] mColumns = new int[]{10, 9, 8};

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

        //cache the attached views first
        mCachedViews = new SparseArray<>(getChildCount());
        for (int i = 0; i < getChildCount(); i++) {
            mCachedViews.put(i, getChildAt(i));
        }

        //detach all attached views
        detachAndScrapAttachedViews(recycler);

        int widthUsed = 0;
        int heightUsed = 0;

        View child = null;


        int dalt = getPaddingStart() + getPaddingEnd();

        int left = getPaddingLeft();
        int top = getPaddingTop();

        for (int i = 0; i < mColumns[0]; i++) {
            child = recycler.getViewForPosition(i);

            measureChildWithMargins(child, widthUsed, heightUsed);
            widthUsed += child.getMeasuredWidth();

            layoutDecoratedWithMargins(child, left, top, left + child.getMeasuredWidth(),
                    top + child.getMeasuredHeight());

            left += child.getMeasuredWidth();
        }

        int fixedWidth = child.getMeasuredWidth();
        int fixedHeight = child.getMeasuredHeight();


        int extra = getWidth() - fixedWidth * mColumns[1] - dalt;
        left = getPaddingLeft() + extra / 2;
        top = getPaddingTop() + fixedHeight;


        for (int j = 0; j < mColumns[1]; j++) {

            child = recycler.getViewForPosition(j + mColumns[0]);

            child.measure(View.MeasureSpec.makeMeasureSpec(fixedWidth, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(fixedHeight, View.MeasureSpec.EXACTLY));

            layoutDecoratedWithMargins(child, left, top, left + child.getMeasuredWidth(),
                    top + child.getMeasuredHeight());

            left += child.getMeasuredWidth();
        }


        int space = (getWidth() - dalt - fixedWidth * (mColumns[2] - 1)) / 2;
        if (space >= fixedWidth) {

            left = getPaddingLeft() + space;
            top = getPaddingTop() + fixedHeight * 2;

            int room = space - fixedWidth;

            for (int k = 0; k < mColumns[2] - 1; k++) {
                child = recycler.getViewForPosition(k + mColumns[0] + mColumns[1]);

                child.measure(View.MeasureSpec.makeMeasureSpec(fixedWidth, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(fixedHeight, View.MeasureSpec.EXACTLY));

                layoutDecoratedWithMargins(child, left, top, left + child.getMeasuredWidth(),
                        top + child.getMeasuredHeight());
                left += child.getMeasuredWidth();
            }

            left += room;
            child = recycler.getViewForPosition(state.getItemCount() - 1);

            child.measure(View.MeasureSpec.makeMeasureSpec(fixedWidth, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(fixedHeight, View.MeasureSpec.EXACTLY));
            layoutDecoratedWithMargins(child, left, top, left + child.getMeasuredWidth(),
                    top + child.getMeasuredHeight());

        } else {

            extra = getWidth() - fixedWidth * mColumns[2] - dalt;
            left = getPaddingLeft() + extra / 2;
            top = getPaddingTop() + fixedHeight * 2;

            for (int k = 0; k < mColumns[2]; k++) {
                child = recycler.getViewForPosition(k + mColumns[0] + mColumns[1]);

                child.measure(View.MeasureSpec.makeMeasureSpec(fixedWidth, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(fixedHeight, View.MeasureSpec.EXACTLY));

                layoutDecoratedWithMargins(child, left, top, left + child.getMeasuredWidth(),
                        top + child.getMeasuredHeight());

                left += child.getMeasuredWidth();

            }


        }


    }


    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
