package cbs.example.opencv_configuration_in_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            // TODO Auto-generated method stub
            switch (status) {
                case BaseLoaderCallback.SUCCESS:
                    Log.i(TAG, "成功加载opencv");
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "成功加载opencv！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    //toast.show();
                    break;
                default:
                    super.onManagerConnected(status);
                    Log.i(TAG, "加载失敗");
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            "加载失敗！", Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER, 0, 0);
                    toast1.show();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button: {
                Intent bitmap_draw = new Intent(MainActivity.this,Bitmap_draw_Activity.class);
                startActivity(bitmap_draw);
                break;
            }
            case R.id.button14: {
                Intent mat_draw = new Intent(MainActivity.this,Mat_draw_Activity.class);
                startActivity(mat_draw);
                break;
            }
            case R.id.button15: {
                Intent read = new Intent(MainActivity.this,Read_picture_Activity.class);
                startActivity(read);
                break;
            }
            case R.id.button16: {
                Intent camera_preview = new Intent(MainActivity.this,Camera_preview.class);
                startActivity(camera_preview);
                break;
            }
            case R.id.button20: {
                Intent blurry = new Intent(MainActivity.this, Blurry_Activity.class);
                startActivity(blurry);
                break;
            }
            case R.id.button21: {
                Intent filter = new Intent(MainActivity.this, Filter_Activity.class);
                startActivity(filter);
                break;
            }
            case R.id.button25: {
                Intent edge_filtering = new Intent(MainActivity.this, Edge_Filtering_Activity.class);
                startActivity(edge_filtering);
                break;
            }
            case R.id.button26: {
                Intent gradient = new Intent(MainActivity.this, Gradient_Activity.class);
                startActivity(gradient);
                break;
            }
            case R.id.button27: {
                Intent laplace_Canny = new Intent(MainActivity.this, Laplace_Canny_Activity.class);
                startActivity(laplace_Canny);
                break;
            }
            case R.id.button28: {
                Intent hough = new Intent(MainActivity.this, Hough_Activity.class);
                startActivity(hough);
                break;
            }
            case R.id.button29: {
                Intent contour = new Intent(MainActivity.this, Contour_Activity.class);
                startActivity(contour);
                break;
            }
            case R.id.button30: {
                Intent model_matching = new Intent(MainActivity.this, Model_matching_Activity.class);
                startActivity(model_matching);
                break;
            }
            case R.id.button31: {
                Intent harris = new Intent(MainActivity.this, Harris_Activity.class);
                startActivity(harris);
                break;
            }
            case R.id.button32: {
                Intent haar = new Intent(MainActivity.this, Haar_Activity.class);
                startActivity(haar);
                break;
            }
            case R.id.button33: {
                Intent rtsp = new Intent(MainActivity.this, RTSP_Activity.class);
                startActivity(rtsp);
                break;
            }
            case R.id.button34: {
                Intent camera_haar = new Intent(MainActivity.this, Camera_HAAR_Activity.class);
                startActivity(camera_haar);
                break;
            }
            case R.id.button35: {
                Intent rtsp_haar = new Intent(MainActivity.this, RTSP_HAAR_Activity.class);
                startActivity(rtsp_haar);
                break;
            }
            case R.id.button36: {
                Intent media_haar = new Intent(MainActivity.this, Media_HAAR_Activity.class);
                startActivity(media_haar);
                break;
            }
            case R.id.button37: {
                Intent orb = new Intent(MainActivity.this, ORB_Activity.class);
                startActivity(orb);
                break;
            }
            case R.id.button38: {
                Intent brisk = new Intent(MainActivity.this, BRISK_Activity.class);
                startActivity(brisk);
                break;
            }
            case R.id.button39: {
                Intent akaze = new Intent(MainActivity.this, AKAZE_Activity.class);
                startActivity(akaze);
                break;
            }
            case R.id.button40: {
                Intent image_crop = new Intent(MainActivity.this, Image_crop_Activity.class);
                startActivity(image_crop);
                break;
            }
            case R.id.button41: {
                Intent histograms = new Intent(MainActivity.this, Histograms_Activity.class);
                startActivity(histograms);
                break;
            }
            case R.id.button42: {
                Intent histograms_compare = new Intent(MainActivity.this, Histograms_Compare_Activity.class);
                startActivity(histograms_compare);
                break;
            }
            default: {
                Toast.makeText(getApplicationContext(),"Button is not definition",Toast.LENGTH_SHORT).show();
            }
        }
    }
}