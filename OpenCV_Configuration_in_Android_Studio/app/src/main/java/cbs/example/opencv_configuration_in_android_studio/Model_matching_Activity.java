package cbs.example.opencv_configuration_in_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class Model_matching_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Mat src,mat;
    private Scalar scalar = new Scalar(255,0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_matching);

        imageView = (ImageView) findViewById(R.id.imageView4);

        try {
            src = Utils.loadResource(Model_matching_Activity.this, R.drawable.gup1);
            Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2RGB);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView9: {
                try {
                    Mat tpl = Utils.loadResource(Model_matching_Activity.this, R.drawable.hana);

                    int height = src.rows() - tpl.rows() + 1;
                    int width = src.width() - tpl.width() + 1;

                    Mat result = new Mat(height,width, CvType.CV_32FC1);

                    int method = Imgproc.TM_CCOEFF_NORMED;

                    Imgproc.matchTemplate(src,tpl,result,method);
                    Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);

                    Point maxloc = minMaxLocResult.maxLoc;
                    Point minloc = minMaxLocResult.minLoc;
                    Point matchloc = null;

                    if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED){
                        matchloc = minloc;
                    }else {
                        matchloc = maxloc;
                    }

                    mat = new Mat();
                    src.copyTo(mat);
                    Imgproc.rectangle(mat,matchloc,new Point(matchloc.x + tpl.cols(),matchloc.y + tpl.rows()), scalar,2,8,0);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView10: {
                try {
                    Mat tpl = Utils.loadResource(Model_matching_Activity.this, R.drawable.miho);

                    int height = src.rows() - tpl.rows() + 1;
                    int width = src.width() - tpl.width() + 1;

                    Mat result = new Mat(height,width, CvType.CV_32FC1);

                    int method = Imgproc.TM_CCOEFF_NORMED;

                    Imgproc.matchTemplate(src,tpl,result,method);
                    Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);

                    Point maxloc = minMaxLocResult.maxLoc;
                    Point minloc = minMaxLocResult.minLoc;
                    Point matchloc = null;

                    if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED){
                        matchloc = minloc;
                    }else {
                        matchloc = maxloc;
                    }

                    mat = new Mat();
                    src.copyTo(mat);
                    Imgproc.rectangle(mat,matchloc,new Point(matchloc.x + tpl.cols(),matchloc.y + tpl.rows()), scalar,2,8,0);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView11: {
                try {
                    Mat tpl = Utils.loadResource(Model_matching_Activity.this, R.drawable.yukari);

                    int height = src.rows() - tpl.rows() + 1;
                    int width = src.width() - tpl.width() + 1;

                    Mat result = new Mat(height,width, CvType.CV_32FC1);

                    int method = Imgproc.TM_CCOEFF_NORMED;

                    Imgproc.matchTemplate(src,tpl,result,method);
                    Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);

                    Point maxloc = minMaxLocResult.maxLoc;
                    Point minloc = minMaxLocResult.minLoc;
                    Point matchloc = null;

                    if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED){
                        matchloc = minloc;
                    }else {
                        matchloc = maxloc;
                    }

                    mat = new Mat();
                    src.copyTo(mat);
                    Imgproc.rectangle(mat,matchloc,new Point(matchloc.x + tpl.cols(),matchloc.y + tpl.rows()), scalar,2,8,0);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView12: {
                try {
                    Mat tpl = Utils.loadResource(Model_matching_Activity.this, R.drawable.saori);

                    int height = src.rows() - tpl.rows() + 1;
                    int width = src.width() - tpl.width() + 1;

                    Mat result = new Mat(height,width, CvType.CV_32FC1);

                    int method = Imgproc.TM_CCOEFF_NORMED;

                    Imgproc.matchTemplate(src,tpl,result,method);
                    Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);

                    Point maxloc = minMaxLocResult.maxLoc;
                    Point minloc = minMaxLocResult.minLoc;
                    Point matchloc = null;

                    if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED){
                        matchloc = minloc;
                    }else {
                        matchloc = maxloc;
                    }

                    mat = new Mat();
                    src.copyTo(mat);
                    Imgproc.rectangle(mat,matchloc,new Point(matchloc.x + tpl.cols(),matchloc.y + tpl.rows()), scalar,2,8,0);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView13: {
                try {
                    Mat tpl = Utils.loadResource(Model_matching_Activity.this, R.drawable.mako);

                    int height = src.rows() - tpl.rows() + 1;
                    int width = src.width() - tpl.width() + 1;

                    Mat result = new Mat(height,width, CvType.CV_32FC1);

                    int method = Imgproc.TM_CCOEFF_NORMED;

                    Imgproc.matchTemplate(src,tpl,result,method);
                    Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);

                    Point maxloc = minMaxLocResult.maxLoc;
                    Point minloc = minMaxLocResult.minLoc;
                    Point matchloc = null;

                    if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED){
                        matchloc = minloc;
                    }else {
                        matchloc = maxloc;
                    }

                    mat = new Mat();
                    src.copyTo(mat);
                    Imgproc.rectangle(mat,matchloc,new Point(matchloc.x + tpl.cols(),matchloc.y + tpl.rows()), scalar,2,8,0);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
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