/*
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2016. Viнt@rь
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.tooltip.tooltip;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import androidx.annotation.ColorInt;


/**
 * Implementation arrow drawable for {@link Tooltip}
 */
final class ArrowDrawable extends ColorDrawable {

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final int mBackgroundColor;
    private final int mGravity;

    private Path mPath;

    ArrowDrawable(@ColorInt int foregroundColor, int gravity) {
        mGravity = gravity;
        mBackgroundColor = Color.TRANSPARENT;

        mPaint.setColor(foregroundColor);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        updatePath(bounds);
    }

    private synchronized void updatePath(Rect bounds) {
        mPath = new Path();

        float x1 = bounds.width() * 5 / 100;
        float y1 = bounds.height() * 5 / 100;

        float x2;
        float y2;

        switch (mGravity) {
            case Gravity.LEFT:
                y1 *= 2.5f;

                x2 = x1 * 10f;
                y2 = y1 * 3f;

                x1 /= 4;
                y1 *= 2;

                mPath.moveTo(0, 0);
                mPath.cubicTo(0, 0, x1, y1, x2, y2);
                mPath.cubicTo(x2, y2, bounds.width() * 1.5f, bounds.height() / 2, x2, bounds.height() - y2);
                mPath.cubicTo(x2, bounds.height() - y2, x1, bounds.height() - y1, 0, bounds.height());
                break;
            case Gravity.TOP:
                x1 *= 2.5f;

                x2 = x1 * 3f;
                y2 = y1 * 10f;

                x1 *= 2;
                y1 /= 4;

                mPath.moveTo(0, 0);
                mPath.cubicTo(0, 0, x1, y1, x2, y2);
                mPath.cubicTo(x2, y2, bounds.width() / 2, bounds.height() * 1.5f, bounds.width() - x2, y2);
                mPath.cubicTo(bounds.width() - x2, y2, bounds.width() - x1, y1, bounds.width(), 0);
                break;
            case Gravity.RIGHT:
                y1 *= 2.5f;

                x2 = x1 * 10f;
                y2 = y1 * 3f;

                x1 /= 4;
                y1 *= 2;

                x1 = bounds.width() - x1;
                x2 = bounds.width() - x2;

                mPath.moveTo(bounds.width(), 0);
                mPath.cubicTo(bounds.width(), 0, x1, y1, x2, y2);
                mPath.cubicTo(x2, y2, -x2, bounds.height() / 2, x2, bounds.height() - y2);
                mPath.cubicTo(x2, bounds.height() - y2, x1, bounds.height() - y1, bounds.width(), bounds.height());
                break;
            case Gravity.BOTTOM:
                x1 *= 2.5f;

                x2 = x1 * 3f;
                y2 = y1 * 10f;

                x1 *= 2;
                y1 /= 4;

                y1 = bounds.height() - y1;
                y2 = bounds.height() - y2;

                mPath.moveTo(0, bounds.height());
                mPath.cubicTo(0, bounds.height(), x1, y1, x2, y2);
                mPath.cubicTo(x2, y2, bounds.width() / 2, -y2, bounds.width() - x2, y2);
                mPath.cubicTo(bounds.width() - x2, y2, bounds.width() - x1, y1, bounds.width(), bounds.height());
                break;
        }

        mPath.close();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(mBackgroundColor);
        if (mPath == null) {
            updatePath(getBounds());
        }
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    public void setColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        if (mPaint.getColorFilter() != null) {
            return PixelFormat.TRANSLUCENT;
        }

        switch (mPaint.getColor() >>> 24) {
            case 255:
                return PixelFormat.OPAQUE;
            case 0:
                return PixelFormat.TRANSPARENT;
        }
        return PixelFormat.TRANSLUCENT;
    }
}
