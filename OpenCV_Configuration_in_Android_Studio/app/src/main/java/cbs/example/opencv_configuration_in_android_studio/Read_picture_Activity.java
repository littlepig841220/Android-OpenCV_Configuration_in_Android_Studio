package cbs.example.opencv_configuration_in_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class Read_picture_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Mat src,mat;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_picture);

        imageView = (ImageView) findViewById(R.id.imageView2);
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
            case R.id.button17: {
                try {
                    src = Utils.loadResource(Read_picture_Activity.this, R.drawable.gup1,Imgcodecs.IMREAD_COLOR);

                    bitmap = Bitmap.createBitmap(src.cols(),src.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(src,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button18: {
                try {
                    src = Utils.loadResource(Read_picture_Activity.this, R.drawable.gup1);

                    mat = new Mat();
                    Imgproc.cvtColor(src,mat,Imgproc.COLOR_BGR2RGB);//(row, column) | (y, x) to (x, y) code is 'getPixelColor'

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button19: {
                try {
                    src = Utils.loadResource(Read_picture_Activity.this, R.drawable.gup1,Imgcodecs.IMREAD_GRAYSCALE);

                    bitmap = Bitmap.createBitmap(src.cols(),src.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(src,bitmap);

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}