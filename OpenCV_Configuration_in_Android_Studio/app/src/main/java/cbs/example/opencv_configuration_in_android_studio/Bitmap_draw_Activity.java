package cbs.example.opencv_configuration_in_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

public class Bitmap_draw_Activity extends AppCompatActivity implements View.OnClickListener{
    private Button line,rectangle,circle,oval,arc,point,round_rectangle,path,text;
    private ImageView imageView;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private int imageView_width;
    private int imageView_height;
    private int offset = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        line = (Button) findViewById(R.id.button2);
        rectangle = (Button) findViewById(R.id.button3);
        circle = (Button) findViewById(R.id.button4);
        oval = (Button) findViewById(R.id.button5);
        arc = (Button) findViewById(R.id.button6);
        point = (Button) findViewById(R.id.button7);
        text = (Button) findViewById(R.id.button10);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            Fullscreen_setting fullscreenSetting = new Fullscreen_setting();
            fullscreenSetting.window = getWindow();
            fullscreenSetting.fullscreen();

            imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    imageView_width = imageView.getWidth();
                    imageView_height = imageView.getHeight();
                    bitmap = Bitmap.createBitmap(imageView_width,imageView_height,Bitmap.Config.ARGB_8888);

                    canvas = new Canvas(bitmap);

                    paint = new Paint();
                    paint.setStrokeWidth(8);
                    paint.setColor(Color.RED);
                    paint.setStyle(Paint.Style.STROKE);//fill:填滿,stroke:邊

                    line.setEnabled(true);
                    rectangle.setEnabled(true);
                    circle.setEnabled(true);
                    oval.setEnabled(true);
                    arc.setEnabled(true);
                    point.setEnabled(true);
                    text.setEnabled(true);
                    return true;
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2: {
                canvas.drawLine(offset,imageView_height/2,imageView_width-offset,imageView_height/2,paint);
                canvas.drawLine(offset,imageView_height/2+100,imageView_width-offset,imageView_height/2+100,paint);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button3: {
                canvas.drawRect(offset,imageView_height/4,imageView_width-offset,imageView_height/4*3,paint);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button4: {
                canvas.drawCircle(imageView_width/2,imageView_height/2,imageView_width/2-offset,paint);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button5: {
                canvas.drawOval(offset,imageView_height/4,imageView_width-offset,imageView_height/4*3,paint);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button6: {
                //canvas.drawArc(offset,canvas.getWidth()/2-offset,canvas.getWidth()-offset,canvas.getWidth()-offset-canvas.getWidth()/2,0,20,true,paint);
                canvas.drawArc(offset,imageView_height/2-(imageView_width/2-offset),imageView_width-offset,imageView_height/2+(imageView_width-offset-imageView_width/2),0,90,true,paint);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button7: {
                canvas.drawPoint(imageView_width/2,imageView_height/2,paint);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button10: {
                paint.setTextSize(100);
                canvas.drawText("test",imageView_width/2,imageView_height/2,paint);
                //canvas.drawText("test",0,0,paint);
                imageView.setImageBitmap(bitmap);
                break;
            }
        }
    }
}