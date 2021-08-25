package org.whitefossa.bluescreen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Class for procedural background generation
 */
public class ProceduralBackgroundGenerator
{
    // Background color
    final private int BackgroundColor = Color.HSVToColor(new float[] { 230f, 1.0f, 0.1f });

    // How many concentric background circles will be drawn
    final private int NumberOfCircles = 20;

    // Concentric circles color
    final private int CircleColor = Color.HSVToColor(new float[] { 266f, 0.87f, 0.3f });


    /**
     * Entry method for background generation
     * @param width Width of target background
     * @param height Height of target background
     * @return Bitmap with background
     */
    public Bitmap GenerateBackground(int width, int height)
    {
        Bitmap resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap);
        Paint paint = new Paint();

        // Enabling antialiasing
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        // Background fill
        paint.setColor(BackgroundColor);
        canvas.drawRect(0f, 0f, width, height, paint);

        // Concentric circles
        DrawBackgroundCircles(canvas, paint);

//        paint.setColor(Color.HSVToColor(new float[] {266f, 0.87f, 1.0f}));
//        paint.setTextSize(24f);
//
//        canvas.drawText("Yiff!", 100, 100, paint);

        return resultBitmap;
    }

    /**
     * Generate concentric background circles
     * @param canvas Where to draw
     * @param  paint With what to draw
     */
    private void DrawBackgroundCircles(Canvas canvas, Paint paint)
    {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        float centerX = width / 2.0f;
        float centerY = height / 2.0f;

        float smallerSize;
        if (centerX < centerY)
        {
            smallerSize = centerX;
        }
        else
        {
            smallerSize = centerY;
        }

        float beltWidth = smallerSize / NumberOfCircles;
        float radius = beltWidth / 2.0f;

        paint.setColor(CircleColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(radius);

        while(radius < centerX)
        {
            canvas.drawCircle(centerX, centerY, radius, paint);

            radius += beltWidth;
        }
    }
    
}
