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
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_PLAIN;


public class Mat_draw_Activity extends AppCompatActivity implements View.OnClickListener {
    //https://docs.opencv.org/master/d3/d63/classcv_1_1Mat.html#a0b57b6a326c8876d944d188a46e0f556
    private Button line,rectangle,circle,oval,text;
    private ImageView imageView;
    private Bitmap bitmap;
    private Mat mat;
    private Scalar scalar = new Scalar(255, 0, 0,255);
    private int imageView_width;
    private int imageView_height;
    private int offset = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        if (OpenCVLoader.initDebug()){
            System.out.println("Opencv loaded");
            //Toast.makeText(getApplicationContext(),"Opencv loaded",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Opencv failed",Toast.LENGTH_SHORT).show();
        }

        line = (Button) findViewById(R.id.button2);
        rectangle = (Button) findViewById(R.id.button3);
        circle = (Button) findViewById(R.id.button4);
        oval = (Button) findViewById(R.id.button5);
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

                    bitmap = Bitmap.createBitmap(imageView_width,imageView_height,Bitmap.Config.ARGB_8888);//大小要和mat一樣
                    mat = Mat.zeros(imageView_height, imageView_width, CvType.CV_8UC4);//畫布大小
                    //mat.create(imageView.getHeight(),imageView.getWidth(),CvType.CV_8UC4);

                    line.setEnabled(true);
                    rectangle.setEnabled(true);
                    circle.setEnabled(true);
                    oval.setEnabled(true);
                    text.setEnabled(true);
                    return true;
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2: {
                Imgproc.line(mat, new Point(imageView_width/2-(imageView_width/2-offset), imageView_height/2-(imageView_width/2-offset)), new Point(imageView_width/2+(imageView_width/2-offset), imageView_height/2+(imageView_width/2-offset)), scalar, 5);
                Imgproc.line(mat, new Point(imageView_width/2+(imageView_width/2-offset), imageView_height/2-(imageView_width/2-offset)), new Point(imageView_width/2-(imageView_width/2-offset), imageView_height/2+(imageView_width/2-offset)), scalar, 5);

                Utils.matToBitmap(mat, bitmap);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button3: {
                Imgproc.rectangle(mat, new Point(offset, imageView_height/4), new Point(imageView_width-offset, imageView_height/4*3), scalar, -1);

                Utils.matToBitmap(mat, bitmap);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button4: {
                Imgproc.circle(mat, new Point(imageView_width/2, imageView_height/2), imageView_width/2-offset,scalar, -1);//x:圓心X點,y:圓心y點,半徑,RGB顏色,粗細(-1:填滿)

                Utils.matToBitmap(mat,bitmap,true);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button5: {
                Imgproc.ellipse(mat, new Point(imageView_width/2, imageView_height/2), new Size((imageView_width-2*offset)/2, (imageView_height/4*3-imageView_height/4)/2), 360, 0, 360, scalar, -1);
                //x:圓心X點,y:圓心y點,width:長軸/2,height:短軸/2,全部角度,起始角度,終止角度,粗細(-1:填滿)

                Utils.matToBitmap(mat, bitmap);
                imageView.setImageBitmap(bitmap);
                break;
            }
            case R.id.button10: {
                Imgproc.putText(mat, "Welcome to OpenCV4Android", new Point(offset, imageView_height/2),FONT_HERSHEY_PLAIN, 4, scalar, 5);
                //text:文字內容,位置,自型,大小,粗細(不可為-1)
                Utils.matToBitmap(mat, bitmap);
                imageView.setImageBitmap(bitmap);
                break;
            }
        }
    }
}