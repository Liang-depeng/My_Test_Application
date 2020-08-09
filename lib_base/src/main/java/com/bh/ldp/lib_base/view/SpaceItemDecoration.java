package com.bh.ldp.lib_base.view;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * created by dapeng at 2018/12/20
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = space;
        outRect.bottom = space;

        int position = parent.getChildLayoutPosition(view);
        if (position % 2 != 0) {
            outRect.right = space;
        }
    }
}
