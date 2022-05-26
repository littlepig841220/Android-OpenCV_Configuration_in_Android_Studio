package cbs.example.opencv_configuration_in_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
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

public class RTSP_Activity extends AppCompatActivity implements TextureView.SurfaceTextureListener, MediaPlayer.OnVideoSizeChangedListener{
    private MediaPlayer mediaPlayer;
    private TextureView textureView;
    private ImageView imageView;
    private String URL = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4";
    //private String URL = "rtsp://110.25.96.68:8555/unicast";
    //private String URL = "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=396&0.592427944422147&t1968=0.09381119532554871";
    private Mat src,mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtsp);

        textureView = (TextureView) findViewById(R.id.textureView);
        imageView = (ImageView) findViewById(R.id.imageView6);

        textureView.setSurfaceTextureListener(this);

        mediaPlayer = new MediaPlayer();
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
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);
        Uri uri = Uri.parse(URL);
        try {
            mediaPlayer.setSurface(surface);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mediaPlayer.setDataSource(getApplicationContext(),uri);
                //mediaPlayer.setDataSource(assetFileDescriptor);
                mediaPlayer.prepareAsync();
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(1f));
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
        mat = new Mat();

        Bitmap bitmap = textureView.getBitmap();
        Utils.bitmapToMat(bitmap,src);

        Imgproc.cvtColor(src,mat,Imgproc.COLOR_BGR2GRAY );

        Utils.matToBitmap(mat,bitmap);
        imageView.setImageBitmap(bitmap);
    }
}