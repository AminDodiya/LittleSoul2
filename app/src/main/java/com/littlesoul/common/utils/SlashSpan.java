package com.littlesoul.common.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;

public class SlashSpan extends ReplacementSpan {

    public SlashSpan() {
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        float[] widths = new float[end - start];
        float[] slashWidth = new float[1];
        paint.getTextWidths(text, start, end, widths);
        paint.getTextWidths("/", slashWidth);
        int sum = (int) slashWidth[0];
        for (int i = 0; i < widths.length; ++i) {
            sum += widths[i];
        }
        return sum;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        String xtext = "/" + text.subSequence(start, end);
        canvas.drawText(xtext, 0, xtext.length(), x, y, paint);
    }
}