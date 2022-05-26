package cbs.example.opencv_configuration_in_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.features2d.AKAZE;
import org.opencv.features2d.BFMatcher;
import org.opencv.features2d.BRISK;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.ORB;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Image_crop_Activity extends AppCompatActivity implements View.OnClickListener{
    private Button button22,button23,button24;
    private ImageView imageView, imageView2;
    private TextView textView;
    private Mat src,color,mat;
    private Bitmap bitmap,bitmap_draw;
    private String[] button_text = new String[3];
    private Canvas canvas;
    private Scalar scalar = new Scalar(255,0,0);
    private Rect rect;

    //http://android-er.blogspot.com/2013/09/detect-touch-and-draw-rect-on-bitmap.html

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compare);

        imageView = (ImageView) findViewById(R.id.imageView5);
        imageView2 = (ImageView) findViewById(R.id.imageView4);
        button22 = (Button) findViewById(R.id.button22);
        button23 = (Button) findViewById(R.id.button23);
        button24 = (Button) findViewById(R.id.button24);
        textView = (TextView) findViewById(R.id.textView2);

        button_text = getApplicationContext().getResources().getStringArray(R.array.images_crop);

        button22.setText(button_text[0]);
        button23.setText(button_text[1]);
        button24.setText(button_text[2]);

        button24.setVisibility(View.GONE);

        //imageView2.setOnTouchListener(this);
        imageView.setVisibility(View.GONE);

        try {

            src = Utils.loadResource(Image_crop_Activity.this, R.drawable.gup1);
            Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2RGB);

            bitmap = Bitmap.createBitmap(src.cols(),src.rows(), Bitmap.Config.ARGB_8888);

            rect = new Rect(40,80,900,700);
            Imgproc.rectangle(src,rect,scalar,5);

            Utils.matToBitmap(src, bitmap);
            imageView2.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Fullscreen_setting fullscreenSetting = new Fullscreen_setting();
            fullscreenSetting.window = getWindow();
            fullscreenSetting.fullscreen();
        }
    }//fullscreen setting

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button22: {
                mat = new Mat(src,rect);

                bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(), Bitmap.Config.ARGB_8888);

                Utils.matToBitmap(mat, bitmap);
                imageView2.setImageBitmap(bitmap);
                break;
            }
            case R.id.button23: {
                try {

                    src = Utils.loadResource(Image_crop_Activity.this, R.drawable.gup1);
                    Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2RGB);

                    bitmap = Bitmap.createBitmap(src.cols(),src.rows(), Bitmap.Config.ARGB_8888);

                    rect = new Rect(40,80,900,700);
                    Imgproc.rectangle(src,rect,scalar,5);

                    Utils.matToBitmap(src, bitmap);
                    imageView2.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}