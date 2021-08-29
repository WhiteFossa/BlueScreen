package org.whitefossa.bluescreen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * Class to draw steps count
 */
public class StepsCountDrawer
{
    // Color settings
    private static float StepsCounterColorHue = 266.0f;
    private static float StepsCounterColorValue = 1.0f;
    private static float StepsCounterColorSaturationAFactor = 0.7f;
    private static float StepsCounterColorSaturationBFactor = 0.3f;

    // Steps counter-related settings
    private static final float StepsPositionX = 0.5f;
    private static final float StepsPositionY = 0.94f;
    private static final float StepsFontSize = 33.0f;
    private static final String StepsFont = "Arial";

    // Steps arc-related settings
    private static final float StepsArcStrokeWidth = 15.0f;

    /**
     * Draw steps counter
     * @param canvas Draw on what
     * @param stepsMade Current steps count
     * @param stepsGoal Steps goal
     */
    public void DrawStepsCounter(Canvas canvas, int stepsMade, int stepsGoal)
    {
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        float stepsPercent = stepsMade / (float) stepsGoal;
        if (stepsPercent > 1.0f)
        {
            stepsPercent = 1.0f;
        }

        int stepsColor = GetStepsCounterColor(stepsPercent);

        // Steps counter (text)
        paint.setColor(stepsColor);
        paint.setTextSize(StepsFontSize);
        paint.setTypeface(Typeface.create(StepsFont, Typeface.NORMAL));

        String stepsString = String.format("%d", stepsMade);

        Rect stepsTextBounds = new Rect();
        paint.getTextBounds(stepsString, 0, stepsString.length(), stepsTextBounds);

        canvas.drawText(String.format("%d", stepsMade),
                StepsPositionX * canvas.getWidth() - stepsTextBounds.width() / 2.0f,
                StepsPositionY * canvas.getHeight() - stepsTextBounds.height() / 2.0f,
                paint);

        // Steps counter (arc)
        RectF stepsArcBounds = new RectF();
        stepsArcBounds.left = 0.0f;
        stepsArcBounds.top = 0.0f;
        stepsArcBounds.right = canvas.getWidth();
        stepsArcBounds.bottom = canvas.getHeight();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(StepsArcStrokeWidth);

        float stepsSweepAngle = 360.0f * stepsPercent;
        float startAngle = 90.0f - (stepsSweepAngle / 2.0f);
        if (startAngle < 0)
        {
            startAngle += 360.0f;
        }

        canvas.drawArc(stepsArcBounds, startAngle, stepsSweepAngle, false, paint);
    }

    /**
     * Returns steps counters color for given steps percent (0 - no steps, 1 - goal achieved)
     * @param stepsPercent Goal percent
     * @return Steps counter color
     */
    private int GetStepsCounterColor(float stepsPercent)
    {
        if (stepsPercent < 0.0f || stepsPercent > 1.0f)
        {
            throw new IllegalArgumentException("Wrong steps percent value");
        }

        float intensity = stepsPercent * StepsCounterColorSaturationAFactor + StepsCounterColorSaturationBFactor;
        if (intensity < 0.0f)
        {
            intensity = 0.0f;
        }
        else if (intensity > 1.0f)
        {
            intensity = 1.0f;
        }

        return Color.HSVToColor(new float[] {StepsCounterColorHue, intensity, StepsCounterColorValue});
    }
}
