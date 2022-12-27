package com.littlesoul.common;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class OverlapDecoration extends RecyclerView.ItemDecoration {

    private final static int vertOverlap = -70;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.set(0, 0, 0, vertOverlap);

    }
}