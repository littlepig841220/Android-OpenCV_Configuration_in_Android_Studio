package cbs.example.opencv_configuration_in_android_studio;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class Hough_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button22,button23,button24;
    private ImageView imageView,imageView2;
    private Mat src,color,mat,canny,line,output,circle;
    private Bitmap bitmap;
    private String[] button_text = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compare);

        imageView = (ImageView) findViewById(R.id.imageView5);
        imageView2 = (ImageView) findViewById(R.id.imageView4);
        button22 = (Button) findViewById(R.id.button22);
        button23 = (Button) findViewById(R.id.button23);
        button24 = (Button) findViewById(R.id.button24);

        button_text = getApplicationContext().getResources().getStringArray(R.array.hough);

        button22.setText(button_text[0]);
        button23.setText(button_text[1]);
        button24.setText(button_text[2]);

        imageView2.setImageResource(R.drawable.shape);
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
                try {
                    src = Utils.loadResource(Hough_Activity.this, R.drawable.shape);

                    color = new Mat();
                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2GRAY);

                    canny = new Mat();
                    Imgproc.Canny(color,canny,50,150,3,true);

                    line = new Mat();
                    Imgproc.HoughLines(canny,line,1,Math.PI/180.0,200);

                    output = new Mat();
                    output = Mat.zeros(src.size(),src.type());

                    float[] data = new float[2];

                    for (int i = 0;i < line.rows();i++){
                        line.get(i,0,data);
                        float rho = data[0];
                        float theta = data[1];

                        double a = Math.cos(theta);
                        double b = Math.sin(theta);

                        double x0 = a*rho;
                        double y0 = b*rho;

                        Point pt1 = new Point();
                        Point pt2 = new Point();

                        pt1.x = Math.round(x0 + 1000*(-b));
                        pt1.y = Math.round(y0 + 1000*(a));

                        pt2.x = Math.round(x0 - 1000*(-b));
                        pt2.y = Math.round(y0 - 1000*(a));

                        Imgproc.line(output,pt1,pt2,new Scalar(255,255,255),3);
                    }

                    mat = new Mat();
                    output.copyTo(mat);

                    bitmap = Bitmap.createBitmap(output.cols(),output.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(output,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button23: {
                try {
                    src = Utils.loadResource(Hough_Activity.this, R.drawable.shape);

                    color = new Mat();
                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2GRAY);

                    canny = new Mat();
                    Imgproc.Canny(color,canny,50,150,3,true);

                    line = new Mat();
                    Imgproc.HoughLinesP(canny,line,1,Math.PI/180.0,100,50,10);

                    output = new Mat();
                    output = Mat.zeros(src.size(),src.type());

                    for (int i = 0;i < line.rows(); i++){
                        int[] one_line = new int[4];
                        line.get(i,0,one_line);
                        Imgproc.line(output,new Point(one_line[0],one_line[1]),new Point(one_line[2],one_line[3]),new Scalar(255,255,255),2,8,0);
                    }

                    mat = new Mat();
                    output.copyTo(mat);

                    bitmap = Bitmap.createBitmap(output.cols(),output.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(output,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button24: {
                try {
                    src = Utils.loadResource(Hough_Activity.this, R.drawable.shape);

                    color = new Mat();
                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2GRAY);

                    canny = new Mat();
                    Imgproc.Canny(color,canny,50,150,3,true);

                    circle = new Mat();
                    Imgproc.HoughCircles(canny,circle,Imgproc.HOUGH_GRADIENT,1,20,100,30,10,200);

                    mat = new Mat();
                    mat.create(src.size(),src.type());
                    for (int i = 0;i < circle.cols(); i++){
                        float[] info = new float[3];
                        circle.get(0,i,info);
                        Imgproc.circle(mat,new Point(info[0],info[1]),(int) info[2],new Scalar(255,255,255),2,8,0);
                    }

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
