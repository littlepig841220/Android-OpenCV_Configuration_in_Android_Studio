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
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class Laplace_Canny_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button22,button23,button24;
    private ImageView imageView;
    private Mat src,color,mat,gaussian_blur,canny,mat_x,mat_y;
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

        button_text = getApplicationContext().getResources().getStringArray(R.array.laplace_Canny);

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
            case R.id.button22: {
                try {
                    src = Utils.loadResource(Laplace_Canny_Activity.this, R.drawable.gup1);
                    color = new Mat();
                    mat = new Mat();

                    Imgproc.cvtColor(src,color,Imgproc.COLOR_BGR2RGB);

                    Imgproc.Laplacian(color,mat,CvType.CV_32F,3,1.0,0);
                    Core.convertScaleAbs(mat,mat);

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button23: {
                /*try {
                    src = Utils.loadResource(Laplace_Canny_Activity.this, R.drawable.gup1, Imgcodecs.IMREAD_GRAYSCALE);
                    mat = new Mat();
                    gaussian_blur = new Mat();
                    canny = new Mat();

                    Imgproc.GaussianBlur(src,gaussian_blur,new Size(3,3),0);

                    Imgproc.Canny(gaussian_blur,canny,50,150,3,true);
                    Core.bitwise_and(gaussian_blur,gaussian_blur,mat,canny);

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //This is a way from book
                try {
                    src = Utils.loadResource(Laplace_Canny_Activity.this, R.drawable.gup1, Imgcodecs.IMREAD_GRAYSCALE);
                    mat = new Mat();
                    mat_x = new Mat();
                    mat_y = new Mat();
                    canny = new Mat();

                    Imgproc.Scharr(src,mat_x,CvType.CV_16S,1,0);
                    Imgproc.Scharr(src,mat_y,CvType.CV_16S,0,1);

                    Imgproc.Canny(mat_x,mat_y,canny,50,150);
                    Core.bitwise_and(src,src,mat,canny);
                    //Core.bitwise_or(src,src,mat,canny);
                    //Core.bitwise_xor(src,src,mat,canny);

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button24: {
                try {
                    src = Utils.loadResource(Laplace_Canny_Activity.this, R.drawable.gup1, Imgcodecs.IMREAD_GRAYSCALE);
                    mat = new Mat();
                    mat_x = new Mat();
                    mat_y = new Mat();
                    canny = new Mat();

                    Imgproc.Sobel(src,mat_x,CvType.CV_16S,1,0);
                    Imgproc.Sobel(src,mat_y,CvType.CV_16S,0,1);

                    Imgproc.Canny(mat_x,mat_y,canny,50,150);
                    Core.bitwise_and(src,src,mat,canny);
                    //Core.bitwise_or(src,src,mat,canny);
                    //Core.bitwise_xor(src,src,mat,canny);

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
