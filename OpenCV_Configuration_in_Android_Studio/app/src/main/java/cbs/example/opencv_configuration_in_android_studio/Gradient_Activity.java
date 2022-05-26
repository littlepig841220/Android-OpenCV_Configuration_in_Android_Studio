package cbs.example.opencv_configuration_in_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class Gradient_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button22,button23,button24;
    private ImageView imageView;
    private Mat src,color,mat,mat_x,mat_y;
    private Bitmap bitmap;
    private String[] button_text = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compare);

        imageView = (ImageView) findViewById(R.id.imageView5);
        button22 = (Button) findViewById(R.id.button22);
        button23 = (Button) findViewById(R.id.button23);
        button24 = (Button) findViewById(R.id.button24);

        button_text = getApplicationContext().getResources().getStringArray(R.array.gradient);

        button22.setText(button_text[0]);
        button23.setText(button_text[1]);
        button24.setText(button_text[2]);

        button24.setVisibility(View.GONE);
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
                    src = Utils.loadResource(Gradient_Activity.this, R.drawable.gup1);
                    color = new Mat();
                    mat_x = new Mat();
                    mat_y = new Mat();
                    mat = new Mat();

                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2RGB);

                    Imgproc.Sobel(color,mat_x, CvType.CV_32F,1,0);
                    Core.convertScaleAbs(mat_x,mat_x);

                    Imgproc.Sobel(color,mat_y,CvType.CV_32F,0,1);
                    Core.convertScaleAbs(mat_y,mat_y);

                    Core.addWeighted(mat_x,0.5,mat_y,0.5,0,mat);

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
                    src = Utils.loadResource(Gradient_Activity.this, R.drawable.gup1);
                    color = new Mat();
                    mat_x = new Mat();
                    mat_y = new Mat();
                    mat = new Mat();

                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2RGB);

                    Imgproc.Scharr(color,mat_x,CvType.CV_32F,1,0);
                    Core.convertScaleAbs(mat_x,mat_x);

                    Imgproc.Scharr(color,mat_y,CvType.CV_32F,0,1);
                    Core.convertScaleAbs(mat_y,mat_y);

                    Core.addWeighted(mat_x,0.5,mat_y,0.5,0,mat);

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