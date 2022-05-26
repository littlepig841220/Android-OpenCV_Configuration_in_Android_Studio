package cbs.example.opencv_configuration_in_android_studio;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_PLAIN;

public class Haar_Activity extends AppCompatActivity implements View.OnClickListener {
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

        button_text = getApplicationContext().getResources().getStringArray(R.array.haar);

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
            case R.id.button22:{
                try {
                    imageView2.setImageResource(R.drawable.shen);

                    src = Utils.loadResource(Haar_Activity.this, R.drawable.shen);
                    Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2RGB);

                    Mat gray = new Mat();
                    Imgproc.cvtColor(src,gray,Imgproc.COLOR_BGR2GRAY);
                    MatOfRect faces = new MatOfRect();

                    InputStream inputStream = getResources().openRawResource(R.raw.lbpcascade_frontalface);

                    File cascadeDir = this.getDir("cascade", Context.MODE_PRIVATE);
                    File file = new File(cascadeDir.getAbsoluteFile(),"lbpcascade_frontalface.xml");

                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] buff = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buff)) != -1){
                        fileOutputStream.write(buff,0,len);
                    }

                    inputStream.close();
                    fileOutputStream.close();
                    CascadeClassifier faceDetector = new CascadeClassifier(file.getAbsolutePath());
                    cascadeDir.delete();

                    faceDetector.detectMultiScale(gray,faces,1.1,3,0,new Size(50,50),new Size());

                    List<Rect> face_list = faces.toList();

                    Mat mat = new Mat();
                    src.copyTo(mat);
                    if (face_list.size() > 0){
                        for (Rect rect : face_list){
                            Imgproc.rectangle(mat,rect.tl(),rect.br(),new Scalar(255,0,0),2,8,0);
                            Imgproc.putText(mat,"face",new Point(rect.x,rect.y),FONT_HERSHEY_PLAIN, 4, new Scalar(255,0,0), 5);
                        }
                    }

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(mat,bitmap);

                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.button23:{
                try {
                    imageView2.setImageResource(R.drawable.car1);

                    src = Utils.loadResource(Haar_Activity.this, R.drawable.car1);
                    Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2RGB);

                    Mat gray = new Mat();
                    Imgproc.cvtColor(src,gray,Imgproc.COLOR_BGR2GRAY);
                    MatOfRect faces = new MatOfRect();

                    InputStream inputStream = getResources().openRawResource(R.raw.car);

                    File cascadeDir = this.getDir("cascade", Context.MODE_PRIVATE);
                    File file = new File(cascadeDir.getAbsoluteFile(),"cars.xml");

                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] buff = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buff)) != -1){
                        fileOutputStream.write(buff,0,len);
                    }

                    inputStream.close();
                    fileOutputStream.close();
                    CascadeClassifier faceDetector = new CascadeClassifier(file.getAbsolutePath());
                    cascadeDir.delete();

                    faceDetector.detectMultiScale(gray,faces,1.1,3,0,new Size(50,50),new Size());

                    List<Rect> face_list = faces.toList();

                    Mat mat = new Mat();
                    src.copyTo(mat);
                    if (face_list.size() > 0){
                        for (Rect rect : face_list){
                            Imgproc.rectangle(mat,rect.tl(),rect.br(),new Scalar(255,0,0),2,8,0);
                            Imgproc.putText(mat,"car",new Point(rect.x,rect.y),FONT_HERSHEY_PLAIN, 2, new Scalar(255,0,0), 2);
                        }
                    }

                    bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
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
