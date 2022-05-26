package cbs.example.opencv_configuration_in_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

public class Histograms_Compare_Activity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener {
    ImageView imageView,imageView2,imageView3,imageView4;
    Mat src,mat,hist1,hist2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histograms_compare);

        imageView = (ImageView) findViewById(R.id.imageView14);
        imageView2 = (ImageView) findViewById(R.id.imageView15);
        imageView3 = (ImageView) findViewById(R.id.imageView16);
        imageView4 = (ImageView) findViewById(R.id.imageView17);

        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView4.setOnLongClickListener(this);
        imageView2.setOnLongClickListener(this);
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
            case R.id.imageView16: {
                try {
                    src = new Mat();
                    mat = new Mat();
                    hist1 = new Mat();

                    Mat gup1 = Utils.loadResource(Histograms_Compare_Activity.this, R.drawable.tiger1);
                    Imgproc.cvtColor(gup1,src,Imgproc.COLOR_BGR2GRAY);

                    List<Mat> images = new ArrayList<>();
                    images.add(src);

                    Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
                    hist1 = new Mat();

                    Imgproc.calcHist(images,new MatOfInt(0),mask,hist1,new MatOfInt(256),new MatOfFloat(0,255));

                    Core.normalize(hist1,hist1,0,255,Core.NORM_MINMAX);

                    int height = hist1.rows();

                    mat.create(400,400,src.type());
                    mat.setTo(new Scalar(200,200,200));

                    float[] hist_data = new float[256];
                    hist1.get(0,0,hist_data);

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

                    imageView3.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView17: {
                imageView2.setImageResource(R.drawable.tiger2);
                try {
                    src = new Mat();
                    mat = new Mat();
                    hist2 = new Mat();

                    Mat gup1 = Utils.loadResource(Histograms_Compare_Activity.this, R.drawable.tiger2);
                    Imgproc.cvtColor(gup1,src,Imgproc.COLOR_BGR2GRAY);

                    List<Mat> images = new ArrayList<>();
                    images.add(src);

                    Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
                    hist2 = new Mat();

                    Imgproc.calcHist(images,new MatOfInt(0),mask,hist2,new MatOfInt(256),new MatOfFloat(0,255));

                    Core.normalize(hist2,hist2,0,255,Core.NORM_MINMAX);

                    int height = hist2.rows();

                    mat.create(400,400,src.type());
                    mat.setTo(new Scalar(200,200,200));

                    float[] hist_data = new float[256];
                    hist2.get(0,0,hist_data);

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

                    imageView4.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView15: {
                imageView2.setImageResource(R.drawable.tiger1);
                try {
                    src = new Mat();
                    mat = new Mat();
                    hist2 = new Mat();

                    Mat gup1 = Utils.loadResource(Histograms_Compare_Activity.this, R.drawable.tiger1);
                    Imgproc.cvtColor(gup1,src,Imgproc.COLOR_BGR2GRAY);

                    List<Mat> images = new ArrayList<>();
                    images.add(src);

                    Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
                    hist2 = new Mat();

                    Imgproc.calcHist(images,new MatOfInt(0),mask,hist2,new MatOfInt(256),new MatOfFloat(0,255));

                    Core.normalize(hist2,hist2,0,255,Core.NORM_MINMAX);

                    int height = hist2.rows();

                    mat.create(400,400,src.type());
                    mat.setTo(new Scalar(200,200,200));

                    float[] hist_data = new float[256];
                    hist2.get(0,0,hist_data);

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

                    imageView4.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button43: {
                if (!(hist1 == null) && !(hist2 == null)){
                    Double result = Imgproc.compareHist(hist1,hist2,Imgproc.HISTCMP_CORREL);
                    Toast.makeText(getApplicationContext(),String.valueOf(result),Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button44: {
                if (!(hist1 == null) && !(hist2 == null)){
                    Double result = Imgproc.compareHist(hist1,hist2,Imgproc.HISTCMP_CHISQR);
                    Toast.makeText(getApplicationContext(),String.valueOf(result),Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button45: {
                if (!(hist1 == null) && !(hist2 == null)){
                    Double result = Imgproc.compareHist(hist1,hist2,Imgproc.HISTCMP_INTERSECT);
                    Toast.makeText(getApplicationContext(),String.valueOf(result),Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button46: {
                if (!(hist1 == null) && !(hist2 == null)){
                    Double result = Imgproc.compareHist(hist1,hist2,Imgproc.HISTCMP_BHATTACHARYYA);
                    Toast.makeText(getApplicationContext(),String.valueOf(result),Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button47: {
                if (!(hist1 == null) && !(hist2 == null)){
                    Double result = Imgproc.compareHist(hist1,hist2,Imgproc.HISTCMP_HELLINGER);
                    Toast.makeText(getApplicationContext(),String.valueOf(result),Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button49: {
                if (!(hist1 == null) && !(hist2 == null)){
                    Double result = Imgproc.compareHist(hist1,hist2,Imgproc.HISTCMP_CHISQR_ALT);
                    Toast.makeText(getApplicationContext(),String.valueOf(result),Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button50: {
                if (!(hist1 == null) && !(hist2 == null)){
                    Double result = Imgproc.compareHist(hist1,hist2,Imgproc.HISTCMP_KL_DIV);
                    Toast.makeText(getApplicationContext(),String.valueOf(result),Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.imageView17:{
                imageView2.setImageResource(R.drawable.panzer);

                try {
                    src = new Mat();
                    mat = new Mat();
                    hist2 = new Mat();

                    Mat gup1 = Utils.loadResource(Histograms_Compare_Activity.this, R.drawable.panzer);
                    Imgproc.cvtColor(gup1,src,Imgproc.COLOR_BGR2GRAY);

                    List<Mat> images = new ArrayList<>();
                    images.add(src);

                    Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
                    hist2 = new Mat();

                    Imgproc.calcHist(images,new MatOfInt(0),mask,hist2,new MatOfInt(256),new MatOfFloat(0,255));

                    Core.normalize(hist2,hist2,0,255,Core.NORM_MINMAX);

                    int height = hist2.rows();

                    mat.create(400,400,src.type());
                    mat.setTo(new Scalar(200,200,200));

                    float[] hist_data = new float[256];
                    hist2.get(0,0,hist_data);

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

                    imageView4.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView15: {
                imageView2.setImageResource(R.drawable.tiger3);

                try {
                    src = new Mat();
                    mat = new Mat();
                    hist2 = new Mat();

                    Mat gup1 = Utils.loadResource(Histograms_Compare_Activity.this, R.drawable.tiger3);
                    Imgproc.cvtColor(gup1,src,Imgproc.COLOR_BGR2GRAY);

                    List<Mat> images = new ArrayList<>();
                    images.add(src);

                    Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
                    hist2 = new Mat();

                    Imgproc.calcHist(images,new MatOfInt(0),mask,hist2,new MatOfInt(256),new MatOfFloat(0,255));

                    Core.normalize(hist2,hist2,0,255,Core.NORM_MINMAX);

                    int height = hist2.rows();

                    mat.create(400,400,src.type());
                    mat.setTo(new Scalar(200,200,200));

                    float[] hist_data = new float[256];
                    hist2.get(0,0,hist_data);

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

                    imageView4.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
        }
        return true;
    }
}