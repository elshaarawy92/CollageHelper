package com.example.collagehelper.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.example.collagehelper.R;
import com.example.collagehelper.base.MyApplication;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liang on 2019/3/18
 */
public class CameraCircleImageView extends CircleImageView {

    private static Bitmap camera = null;
    private static Paint paint;

    static {
        camera = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.camera);
        paint = new Paint();
        paint.setColor(Color.parseColor("#F0F0F0"));
//        paint.setAlpha(100);
        paint.setStyle(Paint.Style.FILL);
    }

    public CameraCircleImageView(Context context) {
        super(context);
    }

    public CameraCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(canvas.getWidth() - 40, canvas.getHeight() - 40, 40,paint);
        Rect rect = new Rect(canvas.getWidth() - 80, canvas.getHeight() - 80, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(camera,null,rect,null);
    }
}
