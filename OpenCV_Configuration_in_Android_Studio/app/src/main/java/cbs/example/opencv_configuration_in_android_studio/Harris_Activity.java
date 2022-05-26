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
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class Harris_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button22,button23,button24;
    private ImageView imageView,imageView2;
    private Mat src,color;
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

        button_text = getApplicationContext().getResources().getStringArray(R.array.harris);

        button22.setText(button_text[0]);
        button23.setText(button_text[1]);
        button24.setText(button_text[2]);

        button24.setVisibility(View.GONE);
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
                    src = Utils.loadResource(Harris_Activity.this, R.drawable.shape);

                    color = new Mat();
                    Mat response = new Mat();
                    Mat response_norm = new Mat();

                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2GRAY);
                    Imgproc.cornerHarris(color,response,2,3,0.04);
                    Core.normalize(response,response_norm,0,255,Core.NORM_MINMAX, CvType.CV_32F);

                    Mat mat = new Mat();
                    mat.create(src.size(),src.type());
                    src.copyTo(mat);

                    float[] data = new float[1];
                    for (int j = 0;j < response_norm.rows();j++){
                        for (int i = 0;i < response_norm.cols(); i++){
                            response_norm.get(j,i,data);
                            if ((int)data[0] > 100){
                                Imgproc.circle(mat,new Point(i,j),5,new Scalar(255,0,0),2,8,0);
                            }
                        }
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
                try {
                    double k = 0.04;
                    int block_size = 3;
                    double quality_level = 0.01;
                    boolean use_harris_corner = false;

                    src = Utils.loadResource(Harris_Activity.this, R.drawable.shape);

                    color = new Mat();
                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2GRAY);
                    MatOfPoint corner = new MatOfPoint();
                    Imgproc.goodFeaturesToTrack(color,corner,100,quality_level,10,new Mat(),block_size,use_harris_corner,k);

                    Mat mat = new Mat();
                    mat.create(src.size(),src.type());
                    src.copyTo(mat);

                    Point[] points= corner.toArray();
                    for (int i = 0;i < points.length; i++){
                        Imgproc.circle(mat,points[i],5,new Scalar(255,0,0),2,8,0);
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
