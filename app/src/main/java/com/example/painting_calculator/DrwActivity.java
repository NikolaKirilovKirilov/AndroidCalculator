package com.example.painting_calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class DrwActivity extends AppCompatActivity {
    private float float1, float2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the float values from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            float1 = extras.getFloat("firstValue");
            float2 = extras.getFloat("secondValue");
        }

        setContentView(new DrawingView(this));
    }

    private class DrawingView extends View {
        private Paint rectPaint, borderPaint, circlePaint;

        public DrawingView(Context context) {
            super(context);

            // Initialize paints
            rectPaint = new Paint();
            rectPaint.setColor(Color.YELLOW);
            rectPaint.setStyle(Paint.Style.FILL);

            borderPaint = new Paint();
            borderPaint.setColor(Color.BLUE);
            borderPaint.setStyle(Paint.Style.STROKE);
            borderPaint.setStrokeWidth(10f); // 10px border

            circlePaint = new Paint();
            circlePaint.setColor(0xFF800020); // Bordeaux color
            circlePaint.setStyle(Paint.Style.FILL);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Set cyan background
            canvas.drawColor(Color.CYAN);

            // Convert float values from dp to pixels
            float widthPx = float1 * getResources().getDisplayMetrics().density;
            float heightPx = float2 * getResources().getDisplayMetrics().density;

            // Calculate rectangle position (centered)
            float left = (getWidth() - widthPx) / 2;
            float top = (getHeight() - heightPx) / 2;

            // Create and draw rectangle
            RectF rect = new RectF(left, top, left + widthPx, top + heightPx);
            canvas.drawRect(rect, rectPaint);
            canvas.drawRect(rect, borderPaint);

            // Draw circle if float1 < float2
            if (float1 < float2) {
                float centerX = rect.centerX();
                float centerY = rect.centerY();
                float diameter = float1 * getResources().getDisplayMetrics().density;

                canvas.drawCircle(centerX, centerY, diameter/2, circlePaint);
            }
        }
    }
}