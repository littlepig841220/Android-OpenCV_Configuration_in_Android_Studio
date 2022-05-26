package cbs.example.opencv_configuration_in_android_studio;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.AKAZE;
import org.opencv.features2d.BRISK;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class AKAZE_Activity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
    private ImageView imageView,imageView_hana,imageView_miho,imageView_yukari,imageView_saori,imageView_mako;
    private Mat src,resources,mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_matching);

        imageView = (ImageView) findViewById(R.id.imageView4);
        imageView_hana = (ImageView) findViewById(R.id.imageView9);
        imageView_miho = (ImageView) findViewById(R.id.imageView10);
        imageView_yukari = (ImageView) findViewById(R.id.imageView11);
        imageView_saori = (ImageView) findViewById(R.id.imageView12);
        imageView_mako = (ImageView) findViewById(R.id.imageView13);

        imageView.setOnClickListener(this);
        imageView_hana.setOnLongClickListener(this);
        imageView_miho.setOnLongClickListener(this);
        imageView_yukari.setOnLongClickListener(this);
        imageView_saori.setOnLongClickListener(this);
        imageView_mako.setOnLongClickListener(this);
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
        ImageView imageView_select = (ImageView) findViewById(v.getId());
        switch (v.getId()) {
            case R.id.imageView9: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.hana);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    akaze.detect(src,matOfKeyPoint);

                    Features2d.drawKeypoints(src,matOfKeyPoint,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView_select.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView10: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.miho);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    akaze.detect(src,matOfKeyPoint);

                    Features2d.drawKeypoints(src,matOfKeyPoint,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView_select.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView11: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.yukari);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    akaze.detect(src,matOfKeyPoint);

                    Features2d.drawKeypoints(src,matOfKeyPoint,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView_select.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView12: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.saori);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    akaze.detect(src,matOfKeyPoint);

                    Features2d.drawKeypoints(src,matOfKeyPoint,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView_select.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView13: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.mako);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    akaze.detect(src,matOfKeyPoint);

                    Features2d.drawKeypoints(src,matOfKeyPoint,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView_select.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView4: {
                imageView.setImageResource(R.drawable.gup1);
                break;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.imageView9: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    resources = Utils.loadResource(AKAZE_Activity.this, R.drawable.hana);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.hana);
                    Imgproc.cvtColor(hana, src, Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint_box = new MatOfKeyPoint();
                    MatOfKeyPoint matOfKeyPoint_scene = new MatOfKeyPoint();

                    akaze.detect(resources, matOfKeyPoint_box);
                    akaze.detect(src, matOfKeyPoint_scene);

                    Mat descriptor_box = new Mat();
                    Mat descriptor_scene = new Mat();

                    akaze.compute(resources, matOfKeyPoint_box, descriptor_box);
                    akaze.compute(src, matOfKeyPoint_scene, descriptor_scene);

                    MatOfDMatch matOfDMatch = new MatOfDMatch();
                    DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
                    descriptorMatcher.match(descriptor_box, descriptor_scene, matOfDMatch);
                    Features2d.drawMatches(resources, matOfKeyPoint_box, src, matOfKeyPoint_scene, matOfDMatch, mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat, bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView10: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    resources = Utils.loadResource(AKAZE_Activity.this, R.drawable.gup1_scale3);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.miho);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint_box = new MatOfKeyPoint();
                    MatOfKeyPoint matOfKeyPoint_scene = new MatOfKeyPoint();

                    akaze.detect(resources,matOfKeyPoint_box);
                    akaze.detect(src,matOfKeyPoint_scene);

                    Mat descriptor_box = new Mat();
                    Mat descriptor_scene = new Mat();

                    akaze.compute(resources,matOfKeyPoint_box,descriptor_box);
                    akaze.compute(src,matOfKeyPoint_scene,descriptor_scene);

                    MatOfDMatch matOfDMatch = new MatOfDMatch();
                    DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
                    descriptorMatcher.match(descriptor_box,descriptor_scene,matOfDMatch);
                    Features2d.drawMatches(resources,matOfKeyPoint_box,src,matOfKeyPoint_scene,matOfDMatch,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView11: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    resources = Utils.loadResource(AKAZE_Activity.this, R.drawable.gup1_scale2);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.yukari);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint_box = new MatOfKeyPoint();
                    MatOfKeyPoint matOfKeyPoint_scene = new MatOfKeyPoint();

                    akaze.detect(resources,matOfKeyPoint_box);
                    akaze.detect(src,matOfKeyPoint_scene);

                    Mat descriptor_box = new Mat();
                    Mat descriptor_scene = new Mat();

                    akaze.compute(resources,matOfKeyPoint_box,descriptor_box);
                    akaze.compute(src,matOfKeyPoint_scene,descriptor_scene);

                    MatOfDMatch matOfDMatch = new MatOfDMatch();
                    DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
                    descriptorMatcher.match(descriptor_box,descriptor_scene,matOfDMatch);
                    Features2d.drawMatches(resources,matOfKeyPoint_box,src,matOfKeyPoint_scene,matOfDMatch,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView12: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    resources = Utils.loadResource(AKAZE_Activity.this, R.drawable.gup1_scale1);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.saori);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint_box = new MatOfKeyPoint();
                    MatOfKeyPoint matOfKeyPoint_scene = new MatOfKeyPoint();

                    akaze.detect(resources,matOfKeyPoint_box);
                    akaze.detect(src,matOfKeyPoint_scene);

                    Mat descriptor_box = new Mat();
                    Mat descriptor_scene = new Mat();

                    akaze.compute(resources,matOfKeyPoint_box,descriptor_box);
                    akaze.compute(src,matOfKeyPoint_scene,descriptor_scene);

                    MatOfDMatch matOfDMatch = new MatOfDMatch();
                    DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
                    descriptorMatcher.match(descriptor_box,descriptor_scene,matOfDMatch);
                    Features2d.drawMatches(resources,matOfKeyPoint_box,src,matOfKeyPoint_scene,matOfDMatch,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.imageView13: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    resources = Utils.loadResource(AKAZE_Activity.this, R.drawable.gup1);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat hana = Utils.loadResource(AKAZE_Activity.this, R.drawable.mako);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    AKAZE akaze = AKAZE.create();

                    MatOfKeyPoint matOfKeyPoint_box = new MatOfKeyPoint();
                    MatOfKeyPoint matOfKeyPoint_scene = new MatOfKeyPoint();

                    akaze.detect(resources,matOfKeyPoint_box);
                    akaze.detect(src,matOfKeyPoint_scene);

                    Mat descriptor_box = new Mat();
                    Mat descriptor_scene = new Mat();

                    akaze.compute(resources,matOfKeyPoint_box,descriptor_box);
                    akaze.compute(src,matOfKeyPoint_scene,descriptor_scene);

                    MatOfDMatch matOfDMatch = new MatOfDMatch();
                    DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
                    descriptorMatcher.match(descriptor_box,descriptor_scene,matOfDMatch);
                    Features2d.drawMatches(resources,matOfKeyPoint_box,src,matOfKeyPoint_scene,matOfDMatch,mat);

                    Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
        }
        return true;
    }
}
