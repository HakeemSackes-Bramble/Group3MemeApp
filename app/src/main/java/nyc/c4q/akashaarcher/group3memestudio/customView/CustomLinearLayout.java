package nyc.c4q.akashaarcher.group3memestudio.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by shawnspeaks on 1/16/17.
 */

public class CustomLinearLayout extends LinearLayout {

    private int paintColor = Color.RED;
    private Paint drawPaint;
    private Path drawPath;

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        createPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(drawPath, drawPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void createPaint(){
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setStrokeWidth(20);
        drawPath = new Path();
    }
}
