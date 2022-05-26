package cbs.example.opencv_configuration_in_android_studio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewTreeObserver;
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

public class Media_HAAR_Activity extends AppCompatActivity implements TextureView.SurfaceTextureListener, MediaPlayer.OnVideoSizeChangedListener{
    private MediaPlayer mediaPlayer;
    private TextureView textureView;
    private ImageView imageView;
    private AssetFileDescriptor assetFileDescriptor;
    private Mat src,gray,gaussian_blur,canny;
    private CascadeClassifier carDetector;
    private int imageView_width;
    private int imageView_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtsp_haar);

        textureView = (TextureView) findViewById(R.id.textureView2);
        imageView = (ImageView) findViewById(R.id.imageView8);

        textureView.setSurfaceTextureListener(this);

        mediaPlayer = new MediaPlayer();

        try {
            assetFileDescriptor = getAssets().openFd("2.mp4");

            InputStream inputStream = getResources().openRawResource(R.raw.car);

            File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
            File file = new File(cascadeDir.getAbsoluteFile(),"car.xml");

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bytes_read = 0;
            while ((bytes_read = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,bytes_read);
            }

            inputStream.close();
            fileOutputStream.close();

            carDetector = new CascadeClassifier(file.getAbsolutePath());
            cascadeDir.delete();
        }catch (IOException e){
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

            imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    imageView_width = imageView.getWidth();
                    imageView_height = imageView.getHeight();
                    return true;
                }
            });
        }
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);
        try {
            mediaPlayer.setSurface(surface);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mediaPlayer.setDataSource(assetFileDescriptor);
                mediaPlayer.prepareAsync();
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(1.0f));
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        src = new Mat();
        gray = new Mat();
        gaussian_blur = new Mat();
        canny = new Mat();

        Bitmap bitmap = textureView.getBitmap();
        Utils.bitmapToMat(bitmap,src);

        Imgproc.cvtColor(src,gray,Imgproc.COLOR_BGR2GRAY );

        Imgproc.GaussianBlur(gray,gaussian_blur,new Size(3,3),0);

        MatOfRect cars = new MatOfRect();
        carDetector.detectMultiScale(gaussian_blur,cars,1.1,17,0,new Size(50,50),new Size());

        List<Rect> face_list = cars.toList();

        if (face_list.size() > 0){
            for (Rect rect : face_list){
                Imgproc.rectangle(src,rect.tl(),rect.br(),new Scalar(255,0,0),3,8,0);
                Imgproc.putText(src,"car",new Point(rect.x,rect.y),FONT_HERSHEY_PLAIN, 4, new Scalar(255,0,0), 4);
                //Imgproc.line(src, new Point(0, imageView_height-100), new Point(imageView_width, imageView_height-100), new Scalar(0,255,0), 5);
            }
        }

        Utils.matToBitmap(src,bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
