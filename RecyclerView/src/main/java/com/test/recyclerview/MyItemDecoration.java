package com.test.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

/**
 * Created by Administrator on 2017/9/27.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration
{
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        outRect.set(5, 5, 5, 5);
//        super.getItemOffsets(outRect, view, parent, state);
    }
}
