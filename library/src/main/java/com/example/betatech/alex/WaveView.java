package com.example.betatech.alex;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {

    public static final int DEFAULT_SQUARE_COLOR = Color.parseColor("#536DFE");

    private Paint paint;
    private Rect rect;

    private int padding;

    private int squareColor = DEFAULT_SQUARE_COLOR;

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
        paint.setColor(Color.MAGENTA);
        rect = new Rect();
    }


    private void init(@Nullable AttributeSet set) {
        init();

        if(set == null){
            return;
        }
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.WaveView);
        squareColor = ta.getColor(R.styleable.WaveView_square_color,DEFAULT_SQUARE_COLOR);
        ta.recycle();



    }

    /**
     * Change color of the square
     *
     * @param colorId Id reference from the colors.xml
     */
    public void setSquareColor(int colorId){
        squareColor = getContext().getResources().getColor(colorId);
        postInvalidate();
    }

    /**
     * Set custom padding around the view
     */
    public void setPaddingAround(int padding){
        this.padding = padding;
        postInvalidate();
    }

    /**
     * Reset the entire view
     */
    public void resetView(){
        padding = 0;
        squareColor = DEFAULT_SQUARE_COLOR;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect.left = padding;
        rect.right = getWidth() - padding;
        rect.top = padding;
        rect.bottom = getHeight() - padding;

        paint.setColor(squareColor);
        canvas.drawRect(rect, paint);

    }
}
