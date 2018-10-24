package com.example.betatech.alex;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class WaveView extends View {

    /* public static final int DEFAULT_SQUARE_COLOR = Color.parseColor("#536DFE");
     private static final int DEFAULT_SQUARE_SIZE = 100;*/
    private static final int DEFAULT_RADIAL_DISTANCE = 20;
    private static final int DEFAULT_RADIAL_COLOR = Color.parseColor("#263238");
    private static final int MAX_NUMBER_OF_CIRCLE = 5;

    private Paint paint;
//    private Rect rect;

    /*private int padding;

    private int squareColor = DEFAULT_SQUARE_COLOR;
    private int squareSize = DEFAULT_SQUARE_SIZE;*/

    private int radialDistance;
    private int offset = 0;
    private int[] radialColors;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init() {
        //mShaderMatrix = new Matrix();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        radialColors = new int[MAX_NUMBER_OF_CIRCLE];
        radialDistance = DEFAULT_RADIAL_DISTANCE;
    }



    private void init(@Nullable AttributeSet set) {
        init();

        if (set == null) {
            return;
        }
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.WaveView);
        radialColors[0] = ta.getColor(R.styleable.WaveView_first_radial_color, DEFAULT_RADIAL_COLOR);
        radialColors[1] = ta.getColor(R.styleable.WaveView_second_radial_color, DEFAULT_RADIAL_COLOR);
        radialColors[2] = ta.getColor(R.styleable.WaveView_third_radial_color, DEFAULT_RADIAL_COLOR);
        radialColors[3] = ta.getColor(R.styleable.WaveView_fourth_radial_color, DEFAULT_RADIAL_COLOR);
        radialColors[4] = ta.getColor(R.styleable.WaveView_fifth_radial_color, DEFAULT_RADIAL_COLOR);
        radialDistance = ta.getDimensionPixelSize(R.styleable.WaveView_radial_distance, DEFAULT_RADIAL_DISTANCE);
        //squareSize = ta.getDimensionPixelSize(R.styleable.WaveView_square_size,DEFAULT_SQUARE_SIZE);
        ta.recycle();


    }

    /**
     * Change color of the square
     *
     * @param colorId Id reference from the colors.xml
     */
    public void setSquareColor(int colorId) {
        //squareColor = getContext().getResources().getColor(colorId);
        //paint.setColor(squareColor);
        postInvalidate();
    }

    /**
     * Set custom padding around the view
     */
    public void setPaddingAround(int padding) {
        //this.padding = padding;
        postInvalidate();
    }

    /**
     * Reset the entire view
     */
    public void resetView() {
        //padding = 0;
        //quareColor = DEFAULT_SQUARE_COLOR;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float circleCenterX = getWidth() /2 ;
        float circleCenterY = getHeight() / 2;
        float circleRadius = getWidth() < getHeight() ? circleCenterX : circleCenterY;
        for (int r = MAX_NUMBER_OF_CIRCLE - 1, i = 1; r >= 0; r--,i++) {
            paint.setColor(radialColors[r]);
            canvas.drawCircle(circleCenterX, circleCenterY, circleRadius -(radialDistance*i) - (offset), paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);
        float x, y;
        double dx, dy;
        float circleCenterX = getWidth() /2 ;
        float circleCenterY = getHeight() / 2;
        float circleRadius = getWidth() < getHeight() ? circleCenterX : circleCenterY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();

                dx = Math.pow(x - circleCenterX, 2);
                dy = Math.pow(y - circleCenterY, 2);

                if (dx + dy < Math.pow(circleRadius, 2)) {
                    offset = radialDistance;
                    postInvalidate();
                    return true;
                }
                return value;
            case MotionEvent.ACTION_UP:
                if (offset != 0) {
                    offset = 0;
                    postInvalidate();
                    return true;
                }
                return value;
        }

        return value;
    }
}
