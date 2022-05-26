package cbs.example.opencv_configuration_in_android_studio;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_PLAIN;
import static org.opencv.imgproc.Imgproc.findContours;

public class Contour_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button22,button23,button24;
    private ImageView imageView,imageView2;
    private TextView textView,textView2;
    private Mat src,color,threshold,mat,hierarchy;
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
        textView = (TextView) findViewById(R.id.textView2);
        textView2 = (TextView) findViewById(R.id.textView3);

        button_text = getApplicationContext().getResources().getStringArray(R.array.contour);

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button22: {
                imageView2.setImageResource(R.drawable.shape);
                textView.setText("");
                textView2.setText("");
                try {
                    src = Utils.loadResource(Contour_Activity.this, R.drawable.shape);

                    color = new Mat();
                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2GRAY);

                    threshold = new Mat();
                    Imgproc.threshold(color,threshold,0,255,Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);

                    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

                    hierarchy = new Mat();
                    Imgproc.findContours(threshold,contours,hierarchy,Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE,new Point(0,0));

                    mat = new Mat();
                    mat.create(src.size(),src.type());

                    for (int i = 0;i < contours.size();i++){
                        Imgproc.drawContours(mat,contours,i,new Scalar(0,0,255),2);
                    }

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button23: {
                imageView2.setImageResource(R.drawable.star);
                textView.setText("Bounding Rect : \n");
                textView2.setText("Min Area Rect : \n");
                try {
                    src = Utils.loadResource(Contour_Activity.this, R.drawable.star);

                    color = new Mat();
                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2GRAY);

                    threshold = new Mat();
                    Imgproc.threshold(color,threshold,0,255,Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);

                    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

                    hierarchy = new Mat();
                    Imgproc.findContours(threshold,contours,hierarchy,Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE,new Point(0,0));

                    mat = new Mat();
                    mat.create(src.size(),src.type());

                    for (int i = 0;i < contours.size();i++){
                        Rect rect = Imgproc.boundingRect(contours.get(i));

                        double bounding_rect_w = rect.width;
                        double bounding_rect_h = rect.height;
                        double bounding_rect_rate = Math.min(bounding_rect_w,bounding_rect_h)/Math.max(bounding_rect_w,bounding_rect_h);

                        textView.append(i + " : \nwidth : " + bounding_rect_w + "\nheight : " + bounding_rect_h + "\nrate : " + bounding_rect_rate + "\n");

                        RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(contours.get(i).toArray()));

                        double min_area_rect_w = rotatedRect.size.width;
                        double min_area_rect_h = rotatedRect.size.height;
                        double min_area_rect_rate = Math.min(min_area_rect_w,min_area_rect_h)/Math.max(min_area_rect_w,min_area_rect_h);

                        textView2.append(i + " : \nwidth : " + min_area_rect_w + "\nheight : " + min_area_rect_h + "\nrate : " + min_area_rect_rate + "\n");

                        Imgproc.drawContours(mat,contours,i,new Scalar(0,0,255),2);
                    }

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button24: {
                imageView2.setImageResource(R.drawable.star);
                textView.setText("Area : \n");
                textView2.setText("Length : \n");
                try {
                    src = Utils.loadResource(Contour_Activity.this, R.drawable.star);

                    color = new Mat();
                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2GRAY);

                    threshold = new Mat();
                    Imgproc.threshold(color,threshold,0,255,Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);

                    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

                    hierarchy = new Mat();
                    Imgproc.findContours(threshold,contours,hierarchy,Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE,new Point(0,0));

                    mat = new Mat();
                    mat.create(src.size(),src.type());

                    for (int i = 0;i < contours.size();i++){
                        double area = Imgproc.contourArea(contours.get(i),false);
                        double arclen = Imgproc.arcLength(new MatOfPoint2f(contours.get(i).toArray()),true);

                        textView.append(i + " : " + area + "\n");
                        textView2.append(i + " : " + arclen + "\n");

                        Imgproc.drawContours(mat,contours,i,new Scalar(0,0,255),2);
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
