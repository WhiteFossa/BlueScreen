package org.whitefossa.bluescreen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Class to draw steps count
 */
public class StepsCountDrawer
{
    // Steps position (in parts of screen dimensions)
    private static final float StepsPositionX = 0.5f;
    private static final float StepsPositionY = 0.94f;
    private static final float StepsFontSize = 33.0f;

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

        int stepsColor = Color.HSVToColor(new float[] {360.0f, 1.0f, 1.0f});

        // Steps counter (text)
        paint.setColor(stepsColor);
        paint.setTextSize(StepsFontSize);

        String stepsString = String.format("%d", stepsMade);

        Rect stepsTextBounds = new Rect();
        paint.getTextBounds(stepsString, 0, stepsString.length(), stepsTextBounds);

        canvas.drawText(String.format("%d", stepsMade),
                StepsPositionX * canvas.getWidth() - stepsTextBounds.width() / 2.0f,
                StepsPositionY * canvas.getHeight() - stepsTextBounds.height() / 2.0f,
                paint);
    }
}
