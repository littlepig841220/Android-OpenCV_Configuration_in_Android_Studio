package cbs.example.opencv_configuration_in_android_studio;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.Utils;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.ORB;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ORB_Activity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
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
        imageView.setOnLongClickListener(this);
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
        switch (v.getId()){
            case R.id.imageView9: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    Mat hana = Utils.loadResource(ORB_Activity.this, R.drawable.hana);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    orb.detect(src,matOfKeyPoint);

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

                    Mat miho = Utils.loadResource(ORB_Activity.this, R.drawable.miho);
                    Imgproc.cvtColor(miho,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    orb.detect(src,matOfKeyPoint);

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

                    Mat yukari = Utils.loadResource(ORB_Activity.this, R.drawable.yukari);
                    Imgproc.cvtColor(yukari,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    orb.detect(src,matOfKeyPoint);

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

                    Mat saori = Utils.loadResource(ORB_Activity.this, R.drawable.saori);
                    Imgproc.cvtColor(saori,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    orb.detect(src,matOfKeyPoint);

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

                    Mat mako = Utils.loadResource(ORB_Activity.this, R.drawable.mako);
                    Imgproc.cvtColor(mako,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    orb.detect(src,matOfKeyPoint);

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

                    resources = Utils.loadResource(ORB_Activity.this, R.drawable.hana);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat hana = Utils.loadResource(ORB_Activity.this, R.drawable.hana);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint_box = new MatOfKeyPoint();
                    MatOfKeyPoint matOfKeyPoint_scene = new MatOfKeyPoint();

                    orb.detect(resources,matOfKeyPoint_box);
                    orb.detect(src,matOfKeyPoint_scene);

                    Mat descriptor_box = new Mat();
                    Mat descriptor_scene = new Mat();

                    orb.compute(resources,matOfKeyPoint_box,descriptor_box);
                    orb.compute(src,matOfKeyPoint_scene,descriptor_scene);

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
            case R.id.imageView10: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    resources = Utils.loadResource(ORB_Activity.this, R.drawable.gup1_scale3);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat miho = Utils.loadResource(ORB_Activity.this, R.drawable.miho);
                    Imgproc.cvtColor(miho,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint_gup = new MatOfKeyPoint();//matOfKeyPoint_box
                    MatOfKeyPoint matOfKeyPoint_hana = new MatOfKeyPoint();//matOfKeyPoint_scene

                    orb.detect(resources,matOfKeyPoint_gup);
                    orb.detect(src,matOfKeyPoint_hana);

                    Mat descriptor_gup = new Mat();//descriptor_box
                    Mat descriptor_hana = new Mat();//descriptor_scene

                    orb.compute(resources,matOfKeyPoint_gup,descriptor_gup);
                    orb.compute(src,matOfKeyPoint_hana,descriptor_hana);

                    MatOfDMatch matOfDMatch = new MatOfDMatch();

                    if (src.type() == resources.type()){
                        //Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();

                        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
                        descriptorMatcher.match(descriptor_gup,descriptor_hana,matOfDMatch);
                    }else {
                        break;
                    }

                    List<DMatch> matchList = matOfDMatch.toList();

                    double max_dist = 0;
                    double min_dist = 100;

                    for (int i = 0;i < matchList.size();i++){
                        Double dist = (double) matchList.get(i).distance;
                        if (dist < min_dist)
                            min_dist = dist;
                        if (dist > max_dist)
                            max_dist = dist;
                    }

                    LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
                    for (int i = 0; i < matchList.size(); i++) {
                        if (matchList.get(i).distance <= (1.5 * min_dist))
                            good_matches.addLast(matchList.get(i));
                    }

                    MatOfDMatch goodMatches = new MatOfDMatch();
                    goodMatches.fromList(good_matches);
                    Mat outputImg = new Mat();
                    MatOfByte drawnMatches = new MatOfByte();
                    if (resources.empty() || resources.cols() < 1 || resources.rows() < 1) {
                        Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
                        break;
                    }

                    Features2d.drawMatches(src, matOfKeyPoint_hana, resources, matOfKeyPoint_gup, goodMatches, outputImg, new Scalar(0,255,0), new Scalar(255,0,0), drawnMatches, Features2d.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS);

                    Bitmap bitmap = Bitmap.createBitmap(outputImg.cols(),outputImg.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(outputImg,bitmap);

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

                    resources = Utils.loadResource(ORB_Activity.this, R.drawable.gup1_scale2);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat yukari = Utils.loadResource(ORB_Activity.this, R.drawable.yukari);
                    Imgproc.cvtColor(yukari,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint_gup = new MatOfKeyPoint();//matOfKeyPoint_box
                    MatOfKeyPoint matOfKeyPoint_hana = new MatOfKeyPoint();//matOfKeyPoint_scene

                    orb.detect(resources,matOfKeyPoint_gup);
                    orb.detect(src,matOfKeyPoint_hana);

                    Mat descriptor_gup = new Mat();//descriptor_box
                    Mat descriptor_hana = new Mat();//descriptor_scene

                    orb.compute(resources,matOfKeyPoint_gup,descriptor_gup);
                    orb.compute(src,matOfKeyPoint_hana,descriptor_hana);

                    MatOfDMatch matOfDMatch = new MatOfDMatch();

                    if (src.type() == resources.type()){
                        //Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();

                        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
                        descriptorMatcher.match(descriptor_gup,descriptor_hana,matOfDMatch);
                    }else {
                        break;
                    }

                    List<DMatch> matchList = matOfDMatch.toList();

                    double max_dist = 0;
                    double min_dist = 100;

                    for (int i = 0;i < matchList.size();i++){
                        Double dist = (double) matchList.get(i).distance;
                        if (dist < min_dist)
                            min_dist = dist;
                        if (dist > max_dist)
                            max_dist = dist;
                    }

                    LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
                    for (int i = 0; i < matchList.size(); i++) {
                        if (matchList.get(i).distance <= (1.5 * min_dist))
                            good_matches.addLast(matchList.get(i));
                    }

                    MatOfDMatch goodMatches = new MatOfDMatch();
                    goodMatches.fromList(good_matches);
                    Mat outputImg = new Mat();
                    MatOfByte drawnMatches = new MatOfByte();
                    if (resources.empty() || resources.cols() < 1 || resources.rows() < 1) {
                        Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
                        break;
                    }

                    Features2d.drawMatches(src, matOfKeyPoint_hana, resources, matOfKeyPoint_gup, goodMatches, outputImg, new Scalar(0,255,0), new Scalar(255,0,0), drawnMatches, Features2d.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS);

                    Bitmap bitmap = Bitmap.createBitmap(outputImg.cols(),outputImg.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(outputImg,bitmap);

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

                    resources = Utils.loadResource(ORB_Activity.this, R.drawable.gup1_scale1);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat saori = Utils.loadResource(ORB_Activity.this, R.drawable.saori);
                    Imgproc.cvtColor(saori,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint_box = new MatOfKeyPoint();
                    MatOfKeyPoint matOfKeyPoint_scene = new MatOfKeyPoint();

                    orb.detect(resources,matOfKeyPoint_box);
                    orb.detect(src,matOfKeyPoint_scene);

                    Mat descriptor_box = new Mat();
                    Mat descriptor_scene = new Mat();

                    orb.compute(resources,matOfKeyPoint_box,descriptor_box);
                    orb.compute(src,matOfKeyPoint_scene,descriptor_scene);

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

                    resources = Utils.loadResource(ORB_Activity.this, R.drawable.gup1);
                    Imgproc.cvtColor(resources,resources,Imgproc.COLOR_BGR2RGB);

                    Mat hana = Utils.loadResource(ORB_Activity.this, R.drawable.mako);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint_box = new MatOfKeyPoint();
                    MatOfKeyPoint matOfKeyPoint_scene = new MatOfKeyPoint();

                    orb.detect(resources,matOfKeyPoint_box);
                    orb.detect(src,matOfKeyPoint_scene);

                    Mat descriptor_box = new Mat();
                    Mat descriptor_scene = new Mat();

                    orb.compute(resources,matOfKeyPoint_box,descriptor_box);
                    orb.compute(src,matOfKeyPoint_scene,descriptor_scene);

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
            case R.id.imageView4: {
                try {
                    mat = new Mat();
                    src = new Mat();

                    Mat hana = Utils.loadResource(ORB_Activity.this, R.drawable.gup1);
                    Imgproc.cvtColor(hana,src,Imgproc.COLOR_BGR2RGB);

                    ORB orb = ORB.create();

                    MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();

                    orb.detect(src,matOfKeyPoint);

                    Features2d.drawKeypoints(src,matOfKeyPoint,mat);

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
