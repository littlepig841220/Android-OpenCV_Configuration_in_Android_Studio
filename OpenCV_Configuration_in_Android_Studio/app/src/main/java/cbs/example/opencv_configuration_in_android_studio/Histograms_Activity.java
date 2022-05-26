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
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Histograms_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button22,button23,button24;
    private ImageView imageView,imageView2;
    private Mat src,mat;
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

        button_text = getApplicationContext().getResources().getStringArray(R.array.histograms);

        button22.setText(button_text[0]);
        button23.setText(button_text[1]);
        button24.setText(button_text[2]);
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
            case R.id.button22:{
                imageView2.setImageResource(R.drawable.gup1);

                try {
                    src = new Mat();
                    mat = new Mat();

                    Mat gup1 = Utils.loadResource(Histograms_Activity.this, R.drawable.gup1);
                    Imgproc.cvtColor(gup1,src,Imgproc.COLOR_BGR2GRAY);

                    List<Mat> images = new ArrayList<>();
                    images.add(src);

                    Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
                    Mat hist = new Mat();

                    Imgproc.calcHist(images,new MatOfInt(0),mask,hist,new MatOfInt(256),new MatOfFloat(0,255));

                    Core.normalize(hist,hist,0,255,Core.NORM_MINMAX);

                    int height = hist.rows();

                    mat.create(400,400,src.type());
                    mat.setTo(new Scalar(200,200,200));

                    float[] hist_data = new float[256];
                    hist.get(0,0,hist_data);

                    int off_set_x = 50;
                    int off_set_y = 350;

                    Imgproc.line(mat,new Point(off_set_x,0),new Point(off_set_x,off_set_y),new Scalar(0,0,0));
                    Imgproc.line(mat,new Point(off_set_x,off_set_y),new Point(400,off_set_y),new Scalar(0,0,0));

                    for (int i = 0;i < height - 1;i++){
                        int y1 = (int) hist_data[i];
                        int y2 = (int) hist_data[i + 1];

                        Rect rect = new Rect();

                        rect.x = off_set_x + i;
                        rect.y = off_set_y - y1;
                        rect.width = 1;
                        rect.height = y1;

                        Imgproc.rectangle(mat,rect.tl(),rect.br(),new Scalar(15,15,15));
                    }

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button23: {
                imageView2.setImageResource(R.drawable.gup2);

                try {
                    src = new Mat();
                    mat = new Mat();

                    Mat gup1 = Utils.loadResource(Histograms_Activity.this, R.drawable.gup2);
                    Imgproc.cvtColor(gup1,src,Imgproc.COLOR_BGR2GRAY);

                    List<Mat> images = new ArrayList<>();
                    images.add(src);

                    Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
                    Mat hist = new Mat();

                    Imgproc.calcHist(images,new MatOfInt(0),mask,hist,new MatOfInt(256),new MatOfFloat(0,255));

                    Core.normalize(hist,hist,0,255,Core.NORM_MINMAX);

                    int height = hist.rows();

                    mat.create(400,400,src.type());
                    mat.setTo(new Scalar(200,200,200));

                    float[] hist_data = new float[256];
                    hist.get(0,0,hist_data);

                    int off_set_x = 50;
                    int off_set_y = 350;

                    Imgproc.line(mat,new Point(off_set_x,0),new Point(off_set_x,off_set_y),new Scalar(0,0,0));
                    Imgproc.line(mat,new Point(off_set_x,off_set_y),new Point(400,off_set_y),new Scalar(0,0,0));

                    for (int i = 0;i < height - 1;i++){
                        int y1 = (int) hist_data[i];
                        int y2 = (int) hist_data[i + 1];

                        Rect rect = new Rect();

                        rect.x = off_set_x + i;
                        rect.y = off_set_y - y1;
                        rect.width = 1;
                        rect.height = y1;

                        Imgproc.rectangle(mat,rect.tl(),rect.br(),new Scalar(15,15,15));
                    }

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button24: {
                imageView2.setImageResource(R.drawable.gup1_small);

                try {
                    src = new Mat();
                    mat = new Mat();

                    Mat gup1 = Utils.loadResource(Histograms_Activity.this, R.drawable.gup1_small);
                    Imgproc.cvtColor(gup1,src,Imgproc.COLOR_BGR2GRAY);

                    List<Mat> images = new ArrayList<>();
                    images.add(src);

                    Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
                    Mat hist = new Mat();

                    Imgproc.calcHist(images,new MatOfInt(0),mask,hist,new MatOfInt(256),new MatOfFloat(0,255));

                    Core.normalize(hist,hist,0,255,Core.NORM_MINMAX);

                    int height = hist.rows();

                    mat.create(400,400,src.type());
                    mat.setTo(new Scalar(200,200,200));

                    float[] hist_data = new float[256];
                    hist.get(0,0,hist_data);

                    int off_set_x = 50;
                    int off_set_y = 350;

                    Imgproc.line(mat,new Point(off_set_x,0),new Point(off_set_x,off_set_y),new Scalar(0,0,0));
                    Imgproc.line(mat,new Point(off_set_x,off_set_y),new Point(400,off_set_y),new Scalar(0,0,0));

                    for (int i = 0;i < height - 1;i++){
                        int y1 = (int) hist_data[i];
                        int y2 = (int) hist_data[i + 1];

                        Rect rect = new Rect();

                        rect.x = off_set_x + i;
                        rect.y = off_set_y - y1;
                        rect.width = 1;
                        rect.height = y1;

                        Imgproc.rectangle(mat,rect.tl(),rect.br(),new Scalar(15,15,15));
                    }

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
